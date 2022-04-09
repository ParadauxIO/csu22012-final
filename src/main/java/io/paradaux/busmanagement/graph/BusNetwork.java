package io.paradaux.busmanagement.graph;

import io.paradaux.busmanagement.data.Stop;
import io.paradaux.busmanagement.data.StopTransfer;
import io.paradaux.busmanagement.data.parse.CSVParser;
import io.paradaux.busmanagement.data.string.TernarySearchTree;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BusNetwork {

    Map<Integer, List<StopNode>> network;
    Map<Integer, Stop> stopRegistry;

    TernarySearchTree stopNameTST;

    public BusNetwork() throws IOException {
        network = new HashMap<>();
        stopRegistry = new HashMap<>();
        stopNameTST = new TernarySearchTree();

        FileWriter writer = new FileWriter("build/out.txt");

        // Registers the stop
        for (Stop s : CSVParser.parseStops()) {
            writer.append(s.toString()).append("\n");
            stopNameTST.insert(s.getName());
            stopRegistry.put(s.getId(), s);
        }

        // Registers the transfers
        generateNetwork();

        writer.close();
        generateNetwork();
;    }

    private void generateNetwork() {

        for (StopTransfer t : CSVParser.parseStopTransfers()) {
            if (t.getType() == 1) {
                makeConnection(t.getFromId(), t.getToId(), 2);
            }

            if (t.getType() == 2) {
                makeConnection(t.getFromId(), t.getToId(), t.getMinTransferTime() / 100d);
            }
        }
    }

    private void makeConnection(int from, int to, double cost) {
        System.out.println(from + " " + to);
        network.computeIfAbsent(from, v -> new ArrayList<>());
        network.computeIfAbsent(to, v -> new ArrayList<>());

        network.get(from).add(new StopNode(to, cost));
    }

    @Nullable
    public List<Stop> getStopsByNamePartial(String namePartial) {
        List<Stop> stops = new ArrayList<>();

        String[] stopNames = stopNameTST.search(namePartial);
        for (String s : stopNames) {

            if (s != null) {



                stops.add(getStopByName(s));
            }

        }

        return stops;
    }

    @Nullable
    public Stop getStopByName(String name) {
        for (Stop s : stopRegistry.values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }

        return null;
    }

    public boolean isValidStopId(int stop_id) {
        return network.containsKey(stop_id);
    }

    public ArrayList<Integer> getShortestPath(int from_stop_id, int to_stop_id) {
        return null;
    }

    public static void main(String[] args) throws IOException {
        BusNetwork network = new BusNetwork();
        System.out.println(network.getShortestPath(3053, 11711));
    }
}

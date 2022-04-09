package io.paradaux.busmanagement.data.structure.graph;

import io.paradaux.busmanagement.data.parse.models.Stop;
import io.paradaux.busmanagement.data.parse.models.StopTime;
import io.paradaux.busmanagement.data.parse.models.StopTransfer;
import io.paradaux.busmanagement.data.parse.CSVParser;
import io.paradaux.busmanagement.data.structure.TernarySearchTree;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class BusNetwork {

    private final Map<Integer, List<StopNode>> network;
    private final Map<Integer, Stop> stopRegistry;
    private final List<StopTime> stopTimes;

    private final TernarySearchTree stopNameTST;

    public BusNetwork() throws IOException {
        network = new HashMap<>();
        stopRegistry = new HashMap<>();
        stopNameTST = new TernarySearchTree();
        stopTimes = CSVParser.parseStopTimes();
        Collections.sort(stopTimes);
//        stopTimes.forEach(System.out::println);


        FileWriter writer = new FileWriter("build/out.txt");

        // Registers the stop
        for (Stop s : CSVParser.parseStops()) {
            writer.append(s.toString()).append("\n");
            stopNameTST.insert(s.getName());
            network.put(s.getId(), new ArrayList<>());
            stopRegistry.put(s.getId(), s);
        }

        // Registers the transfers
        generateNetwork();

        writer.close();
        generateNetwork();
    }

    private void generateNetwork() {
        for (StopTransfer t : CSVParser.parseStopTransfers()) {
            if (t.getType() == 0) {
                makeConnection(t.getFromId(), t.getToId(), 2);
            }

            if (t.getType() == 2) {
                makeConnection(t.getFromId(), t.getToId(), t.getMinTransferTime() / 100d);
            }
        }
    }

    private void makeConnection(int from, int to, double cost) {
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

    /**
     * A method to return the shortest path between two bus stops, representing transfers as edges between nodes (stops)
     *
     * @param source      The Stop ID of the initial bus stop
     * @param destination The Stop ID of the desired destination bus stop.
     * @return An {@link ArrayList<Integer>} containing the stop ids representing the path determined to be the shortest
     * @implNote This method is based on a Java Implementation of the Dijkstra's shortest-path algorithm, for the sake
     *           of ease and due to the time-sensitive nature of returning this assignment. Considering we have both
     *          the longitude and latitude as well as direction (NB, SW) I would not be surprised if it was possible
     *          to implement this same method using the A* shortest-path algorithm, which is an improvement to Dijkstra
     *          with respect to location and direction.
     *          <p>
     *          Portions of this Dijkstra Implementation are taken directly from Algorithms v4 by Robert Sedgewick and
     *          Kevin Wayne
     */
    public ArrayList<Integer> getShortestPath(int source, int destination) {
        if (!network.containsKey(source) || !network.containsKey(destination)) {
            throw new IllegalStateException("The stops presented are not valid. source: " + source + " destination: "
                    + destination);
        }

        if (source == destination) {
            throw new IllegalStateException("The source and destination are the same stop.");
        }

        HashMap<Integer, Double> distanceTable = new HashMap<>(); // StopID:Cost
        HashMap<Integer, Integer> previous = new HashMap<>(); // StopID:StopID
        HashSet<Integer> visited = new HashSet<>(); // StopID

        initialiseDistanceTable(distanceTable, previous, visited);
        distanceTable.put(source, 0.0);

        while (!visited.isEmpty()) {
            int current = Integer.MAX_VALUE;
            double minimum = Double.POSITIVE_INFINITY;

            for (int value : visited) {
                double newVal = distanceTable.get(value);
                if (newVal < minimum) {
                    minimum = newVal;
                    current = value;
                }
            }

            if (current == Integer.MAX_VALUE) {
                break;
            }

            visited.remove(current);

            if (current == destination) {
                break;
            }

            List<StopNode> adjacency = network.getOrDefault(current, new ArrayList<>());

            for (StopNode node : adjacency) {
                if (node.getCost() != Double.POSITIVE_INFINITY && distanceTable.get(current) != null) {
                    double adjDist = distanceTable.get(current) + node.getCost();

                    if (distanceTable.get(node.getStopId()) > adjDist) {
                        distanceTable.put(node.getStopId(), adjDist);
                        previous.put(node.getStopId(), current);
                    }
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();

        if (previous.get(destination) != null) {
            if (previous.get(destination) != Integer.MAX_VALUE) {

                while (destination != Integer.MAX_VALUE) {
                    path.add(0, destination);
                    destination = previous.get(destination);
                }
            }
        }

        return path;
    }

    public List<StopTime> getTripsByArrivalTime(LocalTime time) {
        List<StopTime> matchedTimes = new ArrayList<>();

        for (StopTime st : stopTimes) {
            LocalTime a = st.getArrivalTime();
            boolean b = a.equals(time);
            if (st.getArrivalTime().equals(time)) {
                matchedTimes.add(st);
            }
        }

        return matchedTimes;
    }

    private void initialiseDistanceTable(Map<Integer, Double> distanceTable, HashMap<Integer, Integer> previous, HashSet<Integer> visited) {
        for (int key : network.keySet()) {
            distanceTable.put(key, Double.POSITIVE_INFINITY);
            previous.put(key, Integer.MAX_VALUE);
            visited.add(key);
        }
    }


    public static void main(String[] args) throws IOException {
        BusNetwork network = new BusNetwork();
        System.out.println(network.getShortestPath(1218, 1258));
    }
}

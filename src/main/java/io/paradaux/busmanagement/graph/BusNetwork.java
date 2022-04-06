package io.paradaux.busmanagement.graph;

import io.paradaux.busmanagement.data.Stop;
import io.paradaux.busmanagement.data.parse.CSVParser;
import io.paradaux.busmanagement.data.string.TernarySearchTree;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusNetwork {

    Map<Stop, List<Stop>> network;
    Map<Integer, Stop> stopRegistry;
    TernarySearchTree stopNameTST;

    public BusNetwork() {
        network = new HashMap<>();
        stopRegistry = new HashMap<>();
        stopNameTST = new TernarySearchTree();

        // Registers the stop
        for (Stop s : CSVParser.parseStops()) {
            stopNameTST.insert(s.getName());
            stopRegistry.put(s.getId(), s);
        }

        generateNetwork();
;    }

    private void generateNetwork() {

    }

    @Nullable
    public Stop getStopByNamePartial(String namePartial) {
        return getStopByName(stopNameTST.search(namePartial)[0]);
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

}

package io.paradaux.busmanagement.graph;

import io.paradaux.busmanagement.data.Stop;
import io.paradaux.busmanagement.data.parse.CSVParser;
import io.paradaux.busmanagement.data.string.TernarySearchTree;

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

}

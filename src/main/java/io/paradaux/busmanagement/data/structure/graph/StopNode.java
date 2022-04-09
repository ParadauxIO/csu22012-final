package io.paradaux.busmanagement.data.structure.graph;

public class StopNode {

    private final int stopId;
    private final double cost;

    public StopNode(int stopId, double cost) {
        this.stopId = stopId;
        this.cost = cost;
    }

    public int getStopId() {
        return stopId;
    }

    public double getCost() {
        return cost;
    }
}

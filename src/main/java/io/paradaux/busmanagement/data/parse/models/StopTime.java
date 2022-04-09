package io.paradaux.busmanagement.data.parse.models;

import java.time.LocalTime;

public class StopTime implements Comparable<StopTime> {
    private final int tripId;
    private final LocalTime arrivalTime;
    private final LocalTime departureTime;
    private final int stopId;
    private final int stopSequence;
    private final int stopHeadSign;
    private final int pickupType;
    private final int dropOffData;
    private final double shapeDistTravelled;

    public StopTime(int tripId, LocalTime arrivalTime, LocalTime departureTime, int stopId, int stopSequence, int stopHeadSign, int pickupType, int dropOffData, double shapeDistTravelled) {
        this.tripId = tripId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.stopSequence = stopSequence;
        this.stopHeadSign = stopHeadSign;
        this.pickupType = pickupType;
        this.dropOffData = dropOffData;
        this.shapeDistTravelled = shapeDistTravelled;
    }

    public int getTripId() {
        return tripId;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public int getStopId() {
        return stopId;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public int getStopHeadSign() {
        return stopHeadSign;
    }

    public int getPickupType() {
        return pickupType;
    }

    public int getDropOffData() {
        return dropOffData;
    }

    public double getShapeDistTravelled() {
        return shapeDistTravelled;
    }

    @Override
    public int compareTo(StopTime o) {
        return Integer.compare(this.tripId, o.tripId);
    }

    @Override
    public String toString() {
        return "StopTime{" +
                "tripId=" + tripId +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", stopId=" + stopId +
                ", stopSequence=" + stopSequence +
                ", stopHeadSign=" + stopHeadSign +
                ", pickupType=" + pickupType +
                ", dropOffData=" + dropOffData +
                ", shapeDistTravelled=" + shapeDistTravelled +
                '}';
    }
}

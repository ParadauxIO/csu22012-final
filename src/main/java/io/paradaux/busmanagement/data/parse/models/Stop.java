package io.paradaux.busmanagement.data.parse.models;

public class Stop {
    private final int id;
    private final int code;
    private final String name;
    private final String description;
    private final double latitude;
    private final double longitude;
    private final String zoneId;
    private final int stopUrl;
    private final int locationType;
    private final int parentStation;

    public Stop(int id, int code, String name, String description, double latitude, double longitude, String zoneId, int stopUrl, int locationType, int parentStation) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoneId = zoneId;
        this.stopUrl = stopUrl;
        this.locationType = locationType;
        this.parentStation = parentStation;
    }

    public int getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getZoneId() {
        return zoneId;
    }

    public int getStopUrl() {
        return stopUrl;
    }

    public int getLocationType() {
        return locationType;
    }

    public int getParentStation() {
        return parentStation;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zoneId='" + zoneId + '\'' +
                ", stopUrl=" + stopUrl +
                ", locationType=" + locationType +
                ", parentStation=" + parentStation +
                '}';
    }
}

package io.paradaux.busmanagement.data;

import java.awt.*;

public class Stop {
    private final int id;
    private final int code;
    private final String name;
    private final String description;
    private final double lattitude;
    private final double longitude;
    private final int zoneId;
    private final int stopUrl;
    private final int locationType;
    private final int parentStation;

    public Stop(int id, int code, String name, String description, double lattitude, double longitude, int zoneId, int stopUrl, int locationType, int parentStation) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.lattitude = lattitude;
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

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getZoneId() {
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
}

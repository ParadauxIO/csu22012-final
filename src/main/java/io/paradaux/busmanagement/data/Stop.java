package io.paradaux.busmanagement.data;

import java.awt.*;

public class Stop {
    private final int id;
    private final int code;
    private final String name;
    private final String description;
    private final Point location;
    private final int zoneId;
    private final int stopUrl;
    private final int locationType;
    private final int parentStation;

    public Stop(int id, int code, String name, String description, Point location, int zoneId, int stopUrl, int locationType, int parentStation) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.location = location;
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

    public Point getLocation() {
        return location;
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

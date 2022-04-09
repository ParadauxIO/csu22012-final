package io.paradaux.busmanagement.data.parse.models;

public class StopTransfer {
    private final int fromId;
    private final int toId;
    private final int type;
    private final int minTransferTime;

    public StopTransfer(int fromId, int toId, int type, int minTransferTime) {
        this.fromId = fromId;
        this.toId = toId;
        this.type = type;
        this.minTransferTime = minTransferTime;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public int getType() {
        return type;
    }

    public int getMinTransferTime() {
        return minTransferTime;
    }

    @Override
    public String toString() {
        return "StopTransfer{" +
                "fromId=" + fromId +
                ", toId=" + toId +
                ", type=" + type +
                ", minTransferTime=" + minTransferTime +
                '}';
    }
}

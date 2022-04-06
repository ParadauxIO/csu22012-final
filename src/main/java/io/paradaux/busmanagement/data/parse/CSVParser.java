package io.paradaux.busmanagement.data.parse;

import io.paradaux.busmanagement.data.Stop;
import io.paradaux.busmanagement.data.StopTime;
import io.paradaux.busmanagement.data.StopTransfer;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CSVParser {

    private static final String DELIMITER = ",";

    /**
     * A private constructor to prevent the instantiation of a utility class.
     */
    private CSVParser() {

    }

    private static String[] toStrArray(String str) {
        return str.split(DELIMITER);
    }

    private static List<String[]> readCSVFromPath(String path) {
        List<String> fileContents;
        try {
            fileContents = Files.readAllLines(Path.of(path));
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to retrieve the file contents.");
        }

        // Remove the first entry which contains the column headers.
        fileContents.remove(0);

        LinkedList<String[]> csv = new LinkedList<>();

        for (var line : fileContents) {
            csv.add(toStrArray(line));
        }

        return csv;
    }

    public static List<StopTransfer> parseStopTransfers() {
        List<String[]> stopTransfers = readCSVFromPath("transfers.txt");
        ArrayList<StopTransfer> parsedStopTransfers = new ArrayList<>();

        for (var st : stopTransfers) {
            int from = Integer.parseInt(st[0]);
            int to = Integer.parseInt(st[1]);
            int type = Integer.parseInt(st[2]);
            int minTransferTime = -1;

            if (st.length > 3) {
                minTransferTime = Integer.parseInt(st[3]);
            }

            parsedStopTransfers.add(new StopTransfer(from, to, type, minTransferTime));
        }

        return parsedStopTransfers;
    }

    public static List<Stop> parseStops() {
        List<String[]> stops = readCSVFromPath("stops.txt");
        ArrayList<Stop> parsedStops = new ArrayList<>();

        for (var s : stops) {
            int id = Integer.parseInt(s[0]);

            int code = parseNumberOrDefault(s[1]);
            String name = s[2];
            String description = s[3];
            double latitude = Double.parseDouble(s[4]);
            double longitude = Double.parseDouble(s[5]);
            String zoneId = s[6];
            int stopUrl = parseNumberOrDefault(s[7]);
            int locationType = Integer.parseInt(s[8]);
            int parentStation = -1;

            if (s.length == 10) {
                parentStation = parseNumberOrDefault(s[9]);
            }

            System.out.println(Arrays.toString(s));
            parsedStops.add(new Stop(id, code, name, description, latitude, longitude, zoneId,
                    stopUrl, locationType, parentStation));

        }

        return parsedStops;
    }

    public static List<StopTime> parseStopTime() {
        List<String[]> stopTimes = readCSVFromPath("stop_times.txt");

        ArrayList<StopTime> parsedStopTimes = new ArrayList<>();

        System.out.println(Arrays.toString(stopTimes.get(1)));
        // [9017927,  5:25:00,  5:25:00, 646, 1, , 0, 0]
        for (var st : stopTimes) {
            int tripId = Integer.parseInt(st[0]);
            LocalTime arrivalTime = parseTime(st[1]);
            LocalTime departureTime = parseTime(st[2]);
            int stopId = Integer.parseInt(st[3]);
            int stopSequence = Integer.parseInt(st[4]);
            int stopHeadSign = Integer.parseInt(st[5]);
            int pickupType = Integer.parseInt(st[6]);
            int dropOffData = Integer.parseInt(st[7]);
            int shapeDistTravelled = -1;

            if (st.length > 8) {
                shapeDistTravelled = Integer.parseInt(st[8]);
            }

            parsedStopTimes.add(new StopTime(tripId, arrivalTime, departureTime, stopId,
                    stopSequence, stopHeadSign, pickupType, dropOffData, shapeDistTravelled));
        }

        return parsedStopTimes;
    }

    private static LocalTime parseTime(String str) {
        String[] strs = str.split(":");
        int hours = Integer.parseInt(strs[0]);
        int minutes = Integer.parseInt(strs[1]);
        int seconds = Integer.parseInt(strs[2]);

        return LocalTime.of(hours, minutes, seconds);
    }

    private static int parseNumberOrDefault(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

}

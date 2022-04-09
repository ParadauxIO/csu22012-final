package io.paradaux.busmanagement.data.parse;

import java.time.LocalTime;

public class TimeParser {

    public static LocalTime parseTime(String str) {
        String[] strs = str.split(":");

        if (strs.length < 3) {
            return null;
        }

        int hours = 0, minutes = 0, seconds = 0;
        try {
            hours = Integer.parseInt(strs[0].trim());
            minutes = Integer.parseInt(strs[1].trim());
            seconds = Integer.parseInt(strs[2].trim());

            if (hours > 23 || minutes > 59 || seconds > 59) {
                throw new NumberFormatException("Time is invalid.");
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid time: " + str);
            return null;
        }

        return LocalTime.of(hours, minutes, seconds);
    }
}

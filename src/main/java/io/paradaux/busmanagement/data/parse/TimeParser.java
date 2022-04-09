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
            hours = Integer.parseInt(strs[0]);
            minutes = Integer.parseInt(strs[1]);
            seconds = Integer.parseInt(strs[2]);

            if (hours > 23 || minutes > 60 || seconds > 60) {
                throw new NumberFormatException("Time is invalid.");
            }
        } catch (NumberFormatException exception) {
            return null;
        }

        return LocalTime.of(hours, minutes, seconds);
    }
}

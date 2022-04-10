package io.paradaux.busmanagement.data.parse;

import java.util.ArrayList;
import java.util.List;

public class ParserUtils {
    public static String[] removeFirstCountFromArray(int count, String[] arr) {
        String[] arr2 = new String[arr.length-count];
        System.arraycopy(arr, count, arr2, 0, arr2.length);
        return arr2;
    }

    public static List<String> parseSubstring(char delimiter, String s) {
        System.out.println("s: " + s);
        List<Integer> delimiterLocations = new ArrayList<>();

        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == delimiter) {
                delimiterLocations.add(i);
            }
        }

        if (delimiterLocations.size() % 2 != 0) {
            return new ArrayList<>();
        }


        List<String> stops = new ArrayList<>();
        for (int i = 0; i < delimiterLocations.size() - 1; i+=2) {
            stops.add(s.substring(delimiterLocations.get(i) + 1, delimiterLocations.get(i+1)));
        }

        return stops;
    }

    public static int parseNumberOrDefault(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    public static boolean strArrayContains(String str, String[] arr) {
        for (String s : arr) {
            if (str.equals(s)) {
                return true;
            }
        }

        return false;
    }

}

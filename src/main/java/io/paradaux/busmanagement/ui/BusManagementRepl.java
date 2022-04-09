package io.paradaux.busmanagement.ui;

import de.vandermeer.asciitable.AsciiTable;
import io.paradaux.busmanagement.data.parse.TimeParser;
import io.paradaux.busmanagement.data.parse.models.Stop;
import io.paradaux.busmanagement.data.parse.models.StopTime;
import io.paradaux.busmanagement.data.structure.graph.BusNetwork;

import java.time.LocalTime;
import java.util.*;

public class BusManagementRepl implements Runnable {

    private static final Set<String> EXIT_COMMANDS;

    static {
        final SortedSet<String> exitCommands = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        Collections.addAll(exitCommands, "exit", "quit", "leave");
        EXIT_COMMANDS = Collections.unmodifiableSortedSet(exitCommands);
    }

    private final BusNetwork network;

    public BusManagementRepl(BusNetwork network) {
        this.network = network;
    }

    @Override
    public void run() {
        Scanner consoleIn = new Scanner(System.in);
        printHelp();

        boolean hasQuit = false;
        do {
            System.out.print("journey-planner $ ");
            String operation = consoleIn.nextLine();

            if (EXIT_COMMANDS.contains(operation)) {
                hasQuit = true;
            } else {
                String[] command = operation.split(" ");
                String[] parameters = removeFirstCountFromArray(1, command);
                String conjoinedParameters = String.join(" ", parameters);

                switch (command[0].toLowerCase(Locale.ROOT)) {
                    case "journey": {
                        System.out.println(network.getShortestPath(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
                        // TODO get the cost of going from stops[0] to stops[1]
                        break;
                    }

                    case "lookup": {
                        List<Stop> stops = network.getStopsByNamePartial(conjoinedParameters);

                        if (stops == null) {
                            System.out.println("No stops by this name found.");
                            continue;
                        }

                        break;
                    }

                    case "timesearch": {
                        LocalTime time = TimeParser.parseTime(command[1]);
                        if (time == null) {
                            System.out.println("Invalid time provided.");
                            break;
                        }

                        List<StopTime> matched = network.getTripsByArrivalTime(time);
                        Collections.sort(matched);

                        System.out.println(matched);

                        AsciiTable table = new AsciiTable();
                        table.addRule();
                        table.addRow("ID","Arrival", "Departure");

                        for (StopTime s : matched) {
                            table.addRule();
                            table.addRow("" + s.getStopId(), "" + s.getArrivalTime(), "" + s.getDepartureTime());
                        }

                        System.out.println(table.render());
                        break;
                    }

                    case "?":
                    case "help": {
                        printHelp();
                        break;
                    }

                    case "clear": {
                        for (int i = 0; i < 300; i ++) {
                            System.out.println();
                        }
                        break;
                    }

                    default: {
                        System.out.println("Invalid operation. Type '?' or 'help' for instructions.");
                        break;
                    }
                }
            }
        } while(!hasQuit);

    }

    private void printHelp() {
        System.out.println("Translink Journey Planner | Repl Edition");
        System.out.println("Type '?' or 'help' at any time for further instructions.");
        System.out.println("Strings containing spaces should be encapsulated with quotation marks \"Like this\"\n\n");
        System.out.println("COMMANDS ► ");
        printCommand("journey", "Plan a journey", "journey stop_id stop_id");
        printCommand("lookup", "Look up stop", "lookup <stop> {:stop:}\n");
        printCommand("timesearch", "Search by HH:MM:SS time.", "timesearch 23:59:59");
        System.out.println("Parameters are delimited by space.");
        System.out.println("(<> denotes a required parameter, [] denotes an optional parameter, {} denotes a list of possible parameters)");
    }

    private void printCommand(String n, String d, String u) {
        System.out.printf(" ► %s :: %s%n   %s%n", n, d, u);
    }

    private String[] removeFirstCountFromArray(int count, String[] arr) {
        String[] arr2 = new String[arr.length-count];
        System.arraycopy(arr, count, arr2, 0, arr2.length);
        return arr2;
    }

    private List<String> parseSubstring(char delimiter, String s) {
        System.out.println("s: " + s);
        List<Integer> delimiterLocations = new ArrayList<>();

        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == delimiter) {
                delimiterLocations.add(i);
            }
        }

        System.out.println("delim size: " + delimiterLocations);

        if (delimiterLocations.size() % 2 != 0) {
            return new ArrayList<>();
        }


        List<String> stops = new ArrayList<>();
        for (int i = 0; i < delimiterLocations.size() - 1; i+=2) {
            stops.add(s.substring(delimiterLocations.get(i) + 1, delimiterLocations.get(i+1)));
        }

        System.out.println("stops" + stops);
        return stops;
    }
}

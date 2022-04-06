package io.paradaux.busmanagement.ui;

import io.paradaux.busmanagement.graph.BusNetwork;

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
                        List<String> stops = parseSubstring('"', conjoinedParameters);

                        if (stops.size() != 2) {
                            System.out.println("Invalid options, please provide two stops surrounded by quotation marks.");
                        }

                        // TODO get the cost of going from stops[0] to stops[1]
                        break;
                    }

                    case "lookup": {



                        break;
                    }

                    case "?":
                    case "help": {
                        printHelp();
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
        printCommand("journey", "Plan a journey", "journey :STOP1: :STOP2:");
        printCommand("lookup", "Look up stop", "lookup <stop> {:stop:}\n");
        System.out.println("Parameters are delimited by space.");
        System.out.println("(<> denotes a required parameter, [] denotes an optional parameter, {} denotes a list of possible parameters)");
    }

    private void printCommand(String n, String d, String u) {
        System.out.println(String.format(" ► %s :: %s%n   %s", n, d, u));
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

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

                switch (command[0].toLowerCase(Locale.ROOT)) {
                    case "journey": {
                        System.out.println("d");
                        break;
                    }

                    case "lookup": {
                        System.out.println("l");
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
        System.out.println("Strings containing spaces should be encapsulated with colons :LIKE SO:\n\n");
        System.out.println("COMMANDS ► ");
        printCommand("journey", "Plan a journey", "journey :STOP1: :STOP2:");
        printCommand("lookup", "Look up stop", "lookup <stop> {:stop:}\n");
        System.out.println("Parameters are delimited by space.");
        System.out.println("(<> denotes a required parameter, [] denotes an optional parameter, {} denotes a list of possible parameters)");
    }

    private void printCommand(String n, String d, String u) {
        System.out.println(String.format(" ► %s :: %s%n   %s", n, d, u));
    }
}

package io.paradaux.busmanagement;

import io.paradaux.busmanagement.data.structure.graph.BusNetwork;
import io.paradaux.busmanagement.ui.BusManagementGUI;
import io.paradaux.busmanagement.ui.BusManagementRepl;
import javafx.application.Application;

import java.io.IOException;

import static io.paradaux.busmanagement.data.parse.ParserUtils.strArrayContains;


public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("Bus Management System");

        BusNetwork network = new BusNetwork();

        BusManagementGUI gui = new BusManagementGUI();

        if (strArrayContains("-nogui", args) || (System.getenv("nogui") != null && System.getenv("nogui").equals("true")) ) {
            BusManagementRepl repl = new BusManagementRepl(network);
            repl.run();
        } else {
            Application.launch(BusManagementGUI.class, args);
        }
    }


}

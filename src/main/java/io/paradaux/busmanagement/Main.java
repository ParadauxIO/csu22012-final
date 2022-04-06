package io.paradaux.busmanagement;

import io.paradaux.busmanagement.ui.BusManagementGUI;
import javafx.application.Application;


public class Main {


    public static void main(String[] args) {
        System.out.println("Bus Management System");

//        BusNetwork network = new BusNetwork();

        BusManagementGUI gui = new BusManagementGUI();
        Application.launch(BusManagementGUI.class, args);
    }




}

package io.paradaux.busmanagement;

import io.paradaux.busmanagement.graph.BusNetwork;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Bus Management System");

//        BusNetwork network = new BusNetwork();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 620, 400);
        } catch (IOException e) {
            System.err.println("An error occurred.");
            System.exit(1);
        }

        stage.setTitle("Translink.ca | Journey Planner");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

}

package io.paradaux.busmanagement.ui;

import io.paradaux.busmanagement.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BusManagementGUI extends Application {

    public void launch() {
        Application.launch();
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

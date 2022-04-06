package io.paradaux.busmanagement.ui.controller;

import javafx.fxml.FXML;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField inputText;

    // The reference of outputText will be injected by the FXML loader
    @FXML
    private TextArea outputText;

    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private void initialize() { }

    @FXML
    private void printOutput() {
        outputText.setText(inputText.getText());
    }

}

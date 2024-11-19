package oop.moneymanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import oop.moneymanager.PreferencesHelper;

public class MainScreenNewController implements Initializable{

    @FXML
    Label usernameLabel;

    @FXML
    private Pane viewPane;

    @FXML
    void onAccountInfoButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("AccountInfo");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }

    @FXML
    void onDailyBoardButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("DailyBoard");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }

    @FXML
    void onStatisticalButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("Statistical");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usernameLabel.setText(PreferencesHelper.getUsername().toUpperCase());
        Pane pane = new MultiFxmlController().getPane("DailyBoard");
        viewPane.getChildren().add(pane);
    }
}

package oop.moneymanager.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestEntryController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void changeToLoginScene(ActionEvent event) throws IOException {
      SwitchSceneController controllerSwitch = new SwitchSceneController();
      controllerSwitch.switchToScreenLogin(event);
    }

    @FXML
    protected void changeToMainScene(ActionEvent event) throws IOException {
//      SwitchSceneController controllerSwitch = new SwitchSceneController();
//      controllerSwitch.switchToSceneMain(event);
        SwitchSceneController controllerSwitch = new SwitchSceneController();
        controllerSwitch.switchToScreenifAccount(event);
    }
}
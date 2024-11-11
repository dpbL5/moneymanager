package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneMain(ActionEvent event) throws IOException {
        MainScreenController controller = new MainScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScreenLogin(ActionEvent event) throws IOException {
        // set scene o controller
        LoginScreenController controller = new LoginScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScreenRegister(ActionEvent event) throws IOException {
        RegisterScreenController controller = new RegisterScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

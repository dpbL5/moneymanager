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

    public void switchToMainScreen(ActionEvent event) throws IOException {
        MainScreenController controller = new MainScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLoginScreen(ActionEvent event) throws IOException {
        // set scene o controller
        LoginScreenController controller = new LoginScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRegisterScreen(ActionEvent event) throws IOException {
        RegisterScreenController controller = new RegisterScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAccountInfo(ActionEvent event) throws IOException {
        AccountInfoController controller = new AccountInfoController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

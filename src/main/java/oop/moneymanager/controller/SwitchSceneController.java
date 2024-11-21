package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchSceneController {
    private Stage stage;
    private Scene scene;

    public void switchToMainScreen(ActionEvent event) throws IOException {
        MainScreenNewController controller = new MainScreenNewController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen(); // Center the stage on the screen
        stage.show();
    }
    public void switchToLoginScreen(ActionEvent event) throws IOException {
        // set scene o controller
        LoginScreenController controller = new LoginScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void switchToRegisterScreen(ActionEvent event) throws IOException {
        RegisterScreenController controller = new RegisterScreenController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToAccountInfo(ActionEvent event) throws IOException {
        AccountInfoController controller = new AccountInfoController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void switchToForgotpassword(ActionEvent event) throws IOException {
        ForgotPassController controller = new ForgotPassController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private Scene previousScene;
    public void switchtoChangeInformation(ActionEvent event) throws IOException {
        ChangeInformationController controller = new ChangeInformationController();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

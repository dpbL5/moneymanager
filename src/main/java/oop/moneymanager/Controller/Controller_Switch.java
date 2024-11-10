package oop.moneymanager.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_Switch {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneMain(ActionEvent event) throws IOException {
        Controller_MainScreen controller = new Controller_MainScreen();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScreenLogin(ActionEvent event) throws IOException {
        // set scene o controller
        Controller_Login controller = new Controller_Login();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScreenRegister(ActionEvent event) throws IOException {
        Controller_Register controller = new Controller_Register();
        scene = controller.setScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

package oop.moneymanager;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestEntryController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void changeToLoginScene(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreenView.fxml"));
//        root = loader.load();
//        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        try {
            Controller_Switch controllerSwitch = new Controller_Switch();
            controllerSwitch.switchToScreenLogin(event);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }

    @FXML
    protected void changeToMainScene(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User/MainScreen.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

package oop.moneymanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_MainScreen {


    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User/MainScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }

}

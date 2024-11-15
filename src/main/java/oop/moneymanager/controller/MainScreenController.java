package oop.moneymanager.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainScreenController{
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/MainScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
}

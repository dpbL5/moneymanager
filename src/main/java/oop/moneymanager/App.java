package oop.moneymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Đây là Class gốc cũng như bao gồm cả Entry point của chương trình.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // Directing Scene
            Parent testEntry = FXMLLoader.load(getClass().getResource("LoginScreenView.fxml"));
            primaryStage.setTitle("test");
            primaryStage.setScene(new Scene(testEntry));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
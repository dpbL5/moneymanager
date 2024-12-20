package oop.moneymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            Parent root = FXMLLoader.load(getClass().getResource("/oop/moneymanager/view/LoginScreenView.fxml"));
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(
                new Image(
                    getClass().getResourceAsStream("/oop/moneymanager/images/Logo_zoom.png")
                )
            );            
            primaryStage.setTitle("Money Manager");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
package oop.moneymanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_Login implements Initializable {
    @FXML
    private TextField Username = new TextField();
    @FXML
    private PasswordField Password = new PasswordField();
    @FXML
    private Button login = new Button();
    Controller_Switch switchController = new Controller_Switch();
    @FXML
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreenView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void bttLogIn  (ActionEvent event) throws IOException {
        try{
            String username = Username.getText();
            String password = Password.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Đăng Nhập");
            alert.setHeaderText("Thông báo đăng nhập:");
            System.out.println("heloo");
//        if (loginHandler.isValidLogin(username, password)) {
//            alert.setContentText("Đăng nhập thành công. Chào mừng bạn!");
//            SwitchController switchController = new SwitchController();
//            switchController.switchToSceneMain(event);
//        }
//        else {
//            alert.setContentText("Thông tin đăng nhập không chính xác. Vui lòng kiểm tra lại và thử đăng nhập lại.");
//        }
            alert.show();
            switchController.switchToSceneMain(event);
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println(Username.getText());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
            login.setDisable(true);
            // cai dat mac dinh
//            Username.textProperty().addListener((observable, oldValue, newValue) -> {
//                login.setDisable(newValue.trim().isEmpty());
//            });
        }
       catch (Exception e){
           System.out.println(e.toString());
       }
    }
}

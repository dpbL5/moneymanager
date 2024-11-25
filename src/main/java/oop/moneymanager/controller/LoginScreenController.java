package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import oop.moneymanager.service.LoginHandler;
import oop.moneymanager.service.PreferencesHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password ;
    @FXML
    private Button login ;

    private LoginHandler loginHandle = new LoginHandler();

    @FXML
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/LoginScreenView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void bttLogIn(ActionEvent event) throws IOException {
        try{
            String username = Username.getText().toString();
            String password = Password.getText().toString();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (loginHandle.isValidLogin(username, password)) {
                PreferencesHelper.saveLoginInfo(username, password);
                System.out.println("Login success with username: " + username);
                 alert.setContentText("Login successfully. Welcome !");
                // Dang nhap thanh cong chuyen sang man hinh chinh
                SwitchSceneController switchController = new  SwitchSceneController();
                switchController.switchToMainScreen(event);
            } else {
                alert.setTitle("Login");
                alert.setHeaderText("Login Alert:");
                alert.setContentText("You information is incorrect.Please try again.");
            }
            alert.show();
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println(Username.getText());
        }
    }
    public void bttregister(ActionEvent event) throws IOException {
        SwitchSceneController switchController = new  SwitchSceneController();
        switchController.switchToRegisterScreen(event);
    }
    public void setForgotPass(ActionEvent event) throws IOException {
        SwitchSceneController switchController = new  SwitchSceneController();
        switchController.switchToForgotpassword(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
            login.setDisable(true);
            // cai dat mac dinh
            Username.textProperty().addListener((observable, oldValue, newValue) -> {
                login.setDisable(newValue.trim().isEmpty());
            });

            // bat su kien khi nhan enter
            Password.setOnAction(event -> {
                try {
                    bttLogIn(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
       catch (Exception e){
           System.out.println(e.toString());
       }
    }

}

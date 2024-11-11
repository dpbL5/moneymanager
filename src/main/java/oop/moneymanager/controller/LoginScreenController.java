package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.service.LoginHandle;

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

    private LoginHandle loginHandle = new LoginHandle();

    @FXML
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/LoginScreenView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void bttLogIn  (ActionEvent event) throws IOException {
        try{
            String username = Username.getText().toString();
            String password = Password.getText().toString();

//            String username = "bao1";
//            String password = "12345";
            System.out.println(username + " " + password + " ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Đăng Nhập");
            alert.setHeaderText("Thông báo đăng nhập:");
            System.out.println("heloo");
        if (loginHandle.isValidLogin(username, password)) {
            PreferencesHelper.saveLoginInfo(username, password);
            alert.setContentText("Đăng nhập thành công. Chào mừng bạn!");
            
            SwitchSceneController switchController = new  SwitchSceneController();
            switchController.switchToMainScreen(event);
        }
        else {
            alert.setContentText("Thông tin đăng nhập không chính xác. Vui lòng kiểm tra lại và thử đăng nhập lại.");
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
            login.setDisable(true);
            // cai dat mac dinh
            Username.textProperty().addListener((observable, oldValue, newValue) -> {
                login.setDisable(newValue.trim().isEmpty());
            });
        }
       catch (Exception e){
           System.out.println(e.toString());
       }
    }

}

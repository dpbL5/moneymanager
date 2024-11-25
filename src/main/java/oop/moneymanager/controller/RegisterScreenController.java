package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import oop.moneymanager.service.RegisterHandler;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterScreenController {
    @FXML
    public Button cancel_btn;
    @FXML
    public TextField re_email_fld;
    @FXML
    public TextField re_username_fld;
    @FXML
    private TextField re_phone_fld;
    @FXML
    public TextField re_password_fld;
    @FXML
    public TextField re_confirm_fld;


    private RegisterHandler registerHandle = new RegisterHandler();
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/RegisterScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void setSubmit (ActionEvent event) throws IOException, SQLException {
        try   {
            String emailText = re_email_fld.getText().toString();
            String usernameText = re_username_fld.getText().toString();
            String phoneText = re_phone_fld.getText().toString();
            String passwordText = re_password_fld.getText().toString();
            String confirmPasswordText = re_confirm_fld.getText().toString();
            if(emailText.isEmpty()  || usernameText.isEmpty()  || phoneText.isEmpty()  || passwordText.isEmpty()  || confirmPasswordText.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
                return;
            }
            System.out.println(usernameText + " " + emailText + " " + phoneText + " " + passwordText + " " + confirmPasswordText);
            // Check registration logic
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setHeight(250);
            alert.setHeaderText("Registration Notification!");

            int isRegister = registerHandle.isRegister(usernameText, emailText, phoneText, passwordText, confirmPasswordText);
            if (isRegister == 1) {
                alert.setContentText("You have successfully registered an account.");
                alert.setOnCloseRequest(e -> {
                    SwitchSceneController switchController = new SwitchSceneController();
                    try {
                        switchController.switchToLoginScreen(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            } else if (isRegister == 0) {
                alert.setContentText("Registration failed. Please check your information.\nNote: Username must not contain special characters, must be lowercase and without spaces.");

            }
            else if(isRegister == 5) {
                alert.setContentText("Passwords do not match.");
            }
            else if (isRegister == 4) {
                alert.setContentText("The email is already in use. Please enter another email.\nIf you already have an account but forgot the password, please switch to the login screen and select the 'Forgot Password' feature.");
            } else if (isRegister == 3) {
                alert.setContentText("The phone number is already in use. Please enter another phone number.\nIf you already have an account but forgot the password, please switch to the login screen and select the 'Forgot Password' feature.");
            } else if (isRegister == 2) {
                alert.setContentText("The username is already in use. Please enter another username.\nIf you already have an account but forgot the password, please switch to the login screen and select the 'Forgot Password' feature.");
            }
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setCancel(ActionEvent event) throws IOException {
        SwitchSceneController switchController = new SwitchSceneController();
        switchController.switchToLoginScreen(event);
    }

}

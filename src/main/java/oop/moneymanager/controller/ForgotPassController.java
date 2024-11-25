package oop.moneymanager.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import oop.moneymanager.service.ForgotPasswordHandl;
import java.io.IOException;

public class ForgotPassController {
    @FXML
    private TextField username_field;
    @FXML
    private TextField email_field;

    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/Forgotpassword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void setButtonSubmit(ActionEvent event){
        String userName = username_field.getText();
        String emailText = email_field.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");

        boolean isCheck = ForgotPasswordHandl.getInstance().checkSendMail(userName,emailText);
        if (isCheck) ProgressBarController.getInstance(2500L).showProgressBar();
        Task<Boolean> sendMailTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                boolean isCheckConditions = ForgotPasswordHandl.getInstance().checkSendMail(userName,  emailText);
                if(!isCheckConditions) return false;
                return ForgotPasswordHandl.confirmPasswordResetRequest(userName,emailText);
            }
        };

        sendMailTask.setOnSucceeded(send -> {
            boolean isSend = sendMailTask.getValue();
            if (isSend) {
                alert.setContentText("Thank you for providing the information. We are processing your request.\n" +
                        "Please check your inbox for the new password.\n" +
                        "If you do not receive the email within a few minutes, please check your Spam folder or contact us for assistance.");
                alert.setOnCloseRequest(e -> {
                    SwitchSceneController switchController = new SwitchSceneController();
                    try {
                        switchController.switchToLoginScreen(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            } else {
                alert.setContentText("Sorry, it seems the information you entered is incorrect.\n" +
                        "Please double-check and provide accurate information.");
            }
            alert.show();
        });
        new Thread(sendMailTask).start();
    }
    public  void setButtonCancel(ActionEvent event) throws IOException {
        SwitchSceneController controller = new SwitchSceneController();
        controller.switchToLoginScreen(event);
    }



}

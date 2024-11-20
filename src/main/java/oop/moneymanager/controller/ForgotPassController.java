package oop.moneymanager.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
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
        if (isCheck) ProgressBarController.getInstance(13226L).showProgressBar();
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

                alert.setContentText("Cảm ơn bạn đã cung cấp thông tin. Chúng tôi đang xử lý yêu cầu của bạn.\n" +
                        "Vui lòng kiểm tra hộp thư đến của bạn để nhận mật khẩu mới.\n " +
                        "Nếu bạn không nhận được email trong vòng vài phút, vui lòng kiểm tra thư mục Spam hoặc liên hệ với " +
                        "chúng tôi để được hỗ trợ.");
                alert.setOnCloseRequest(e -> {
                    SwitchSceneController switchController = new SwitchSceneController();
                    try {
                        switchController.switchToLoginScreen(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            } else {
                alert.setContentText("Xin lỗi, có vẻ như thông tin bạn đã nhập không đúng.\n" +
                        "Vui lòng kiểm tra lại và nhập thông tin chính xác.");
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

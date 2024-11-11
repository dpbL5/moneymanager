package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import oop.moneymanager.service.RegisterHandle;
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
    public TextField re_password_fld;
    @FXML
    public TextField re_confirm_fld;


    private RegisterHandle registerHandle = new RegisterHandle();
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
            String passwordText = re_password_fld.getText().toString();
            String confirmPasswordText = re_confirm_fld.getText().toString();
            System.out.println(usernameText + " " + emailText + " " + passwordText + " " + confirmPasswordText);
//            String usernameText = "bao5";
//            String emailText = "bao5@gmail.com";
//            String passwordText = "123456";
//            String confirmPasswordText = "123456";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setHeight(250);
            alert.setHeaderText("Thông báo đăng kí!");
            int isRegister = registerHandle.isRegister(usernameText, emailText, passwordText, confirmPasswordText);
            if (isRegister == 1) {
                alert.setContentText("Bạn đã đăng kí tài khoản thành công");
                // set hanh dong sau khi an ok o alert
                alert.setOnCloseRequest(e -> {
                    // neu thanh cong se ve man hinh login
                    SwitchSceneController switchController = new SwitchSceneController();
                    try {
                        switchController.switchToLoginScreen(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            } else if (isRegister == 0) {
                alert.setContentText("Đăng kí thất bại, vui lòng kiểm tra lại thông tin\nLưu ý: User Name không có kí tự đặc biệt");
            } else if (isRegister == 2) {
                alert.setContentText("username hoặc email đã được sử dụng để tạo tài khoản vui lòng nhập username và email khác " +
                        "\nNếu bạn đã có tài khoản nhưng không nhớ mật khẩu hãy chuyển sang màn hình loing và chọn tính năng Forgot password");
            }
            alert.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setCancel(ActionEvent event) throws IOException {
        SwitchSceneController switchController = new SwitchSceneController();
        switchController.switchToLoginScreen(event);
    }
}

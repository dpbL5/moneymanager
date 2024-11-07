package oop.moneymanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import oop.moneymanager.service.RegisterHandle;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Controller_Register {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;

    private RegisterHandle registerHandle = new RegisterHandle();
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void setSubmit (ActionEvent event) throws IOException, SQLException {
        String usernameText = username.getText();
        String emailText = this.email.getText();
        String passwordText = this.password.getText();
        String confirmPasswordText = this.confirmPassword.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeight(250);
        alert.setHeaderText("Thông báo đăng kí!");
        int isRegister =  registerHandle.isRegister(usernameText,emailText, passwordText, passwordText);
        if (isRegister == 1) {
            alert.setContentText("Bạn đã đăng kí tài khoản thành công");
            // set hanh dong sau khi an ok o alert
            alert.setOnCloseRequest(e -> {
                // neu thanh cong se ve man hinh login
                Controller_Switch switchController = new Controller_Switch();
                try {
                    switchController.switchToScreenLogin(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else if(isRegister == 0){
            alert.setContentText("Đăng kí thất bại, vui lòng kiểm tra lại thông tin\nLưu ý: User Name không có kí tự đặc biệt");
        }
        else if(isRegister == 2){
            alert.setContentText("username hoặc email đã được sử dụng để tạo tài khoản vui lòng nhập username và email khác " +
                    "\nNếu bạn đã có tài khoản nhưng không nhớ mật khẩu hãy chuyển sang màn hình chính và chọn tính năng xin lại mật khẩu");
        }
        alert.show();
    }

    public void setCancel(ActionEvent event) throws IOException {
        Controller_Switch switchController = new Controller_Switch();
        switchController.switchToScreenLogin(event);
    }
}

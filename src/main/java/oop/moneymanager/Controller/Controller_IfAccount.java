package oop.moneymanager.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class Controller_IfAccount implements Initializable {


    @FXML
    private Label username_field;
    @FXML
    private Label email_field;
    @FXML
    private Label phone_field;
    @FXML
    private Label money_field;


    private UserModel user;

    public Controller_IfAccount() {

    }

    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/User/ifAccount.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void btt_logout(ActionEvent event) throws IOException {
        Controller_Switch controller = new Controller_Switch();
        controller.switchToScreenLogin(event);
    }
    public void btt_changepassword(ActionEvent event) throws IOException {
        showInputDialog();
    }
    public void btt_adjust(ActionEvent event) throws IOException {

    }


    // Hàm hiển thị Dialog cho phép nhập username và email
    private void showInputDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Nhập thông tin đăng nhập");

        // Tạo giao diện bên trong Dialog
        VBox dialogContent = new VBox(10);
        dialogContent.setPadding(new Insets(10));

        // TextField cho username
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nhập tên đăng nhập...");

        // TextField cho email
        TextField emailField = new TextField();
        emailField.setPromptText("Nhập email...");

        // TextField cho new password
        TextField passwordText = new TextField();
        passwordText.setPromptText("Nhập mật khẩu mới...");
        Label newpasswordLabel = new Label();

        // Nút "Xác nhận" - là ButtonType
        ButtonType confirmButtonType = new ButtonType("Xác nhận");
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CLOSE);

        // Nút "Xác nhận" xử lý sự kiện
        dialog.getDialogPane().lookupButton(confirmButtonType).setOnMouseEntered(event -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordText.getText();
            if (!username.isEmpty() && username.equals(this.user.getUserName()) && !email.isEmpty() && email.equals(this.user.getEmail()) && !password.isEmpty()) {
                newpasswordLabel.setText("mật khẩu mới đượn tạo là: " + password);
                user.setPassWord(password);
                int row = UserDao.getInstance().update(this.user);
            } else {
                newpasswordLabel.setText("Vui lòng nhập đủ thông tin!");
            }
        });

        // Thêm các phần tử vào VBox
        dialogContent.getChildren().addAll(usernameField, emailField, passwordText,newpasswordLabel);

        // Đặt VBox vào Dialog
        dialog.getDialogPane().setContent(dialogContent);

        // Hiển thị Dialog và chờ người dùng đóng lại
        dialog.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserDao.getInstance().selectByUserName(PreferencesHelper.getUsername());
        username_field.setText(user.getUserName());
        email_field.setText(user.getEmail());
        phone_field.setText(user.getPhone());
        money_field.setText(user.getMoney());
    }

}

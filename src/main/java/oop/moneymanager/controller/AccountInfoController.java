package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;
import oop.moneymanager.service.PreferencesHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AccountInfoController implements Initializable {
    @FXML
    private Label username_field;
    @FXML
    private Label email_field;
    @FXML
    private Label phone_field;


    private UserModel user;

    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/AccountInfo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void btt_changepassword(ActionEvent event) throws IOException {
        showInputDialog();
    }
    public void btt_adjust(ActionEvent event) throws IOException {
        // Tải file FXML dành cho pop-up
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/ChangeInfo.fxml"));
        Parent root = loader.load();

        // Tạo một Stage mới cho pop-up
        Stage popupStage = new Stage();
        popupStage.setTitle("Change Information");
        popupStage.setScene(new Scene(root));

        // Đặt chế độ Modal để khóa giao diện chính
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Hiển thị pop-up
        popupStage.showAndWait();
        update();
    }

private void showInputDialog() {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Change password");

    // Tạo giao diện bên trong Dialog
    VBox dialogContent = new VBox(10);
    dialogContent.setPadding(new Insets(10));

    // TextField cho mật khẩu cũ
    PasswordField oldPasswordField = new PasswordField();
    oldPasswordField.setPromptText("Enter old password...");

    // TextField cho mật khẩu mới
    PasswordField newPasswordField =  new PasswordField();
    newPasswordField.setPromptText("Enter new password...");

    // TextField để nhập lại mật khẩu
    PasswordField confirmPasswordField =  new PasswordField();
    confirmPasswordField.setPromptText("Enter confirm password...");

    // TextField cho mã captcha
    TextField captchaField = new TextField();
    captchaField.setPromptText("Enter captcha...");

    // Label hiển thị mã captcha
    String captcha = UUID.randomUUID().toString().substring(0,6);; // Bạn có thể tạo mã ngẫu nhiên
    Label captchaLabel = new Label(captcha);
    captchaLabel.setStyle("-fx-border-color: black; -fx-padding: 5px;");

    // Nút "Xác nhận" - là ButtonType
    ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CLOSE);

    // Nút "Xác nhận" xử lý sự kiện
    dialog.getDialogPane().lookupButton(confirmButtonType).setOnMousePressed(event -> {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String enteredCaptcha = captchaField.getText();

        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty() || enteredCaptcha.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alert:");
            alert.setContentText("Please enter all the fields correctly.");
            alert.show();
        } else if (!enteredCaptcha.equals(captcha)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Wrong captcha.:");
            alert.setContentText("Please enter the right captcha: " + captcha);
            alert.show();
        } else if (!newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alert:");
            alert.setContentText("the new password do not match.");
            alert.show();
        } else {
            // Giả sử kiểm tra mật khẩu cũ chính xác
            if (oldPassword.equals(this.user.getPassWord())) {
                user.setPassWord(newPassword);
                int row = UserDao.getInstance().update(this.user);
                if (row > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success!:");
                    alert.setContentText("Change password successfully.!");
                    alert.showAndWait();
                    dialog.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Fail:");
                    alert.setContentText("an error occured,please try again.");
                    alert.show();

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("the old password is incorrect.:");
                alert.setContentText("please try again.");
                alert.show();
            }
        }
    });

    // Thêm các trường vào VBox
    dialogContent.getChildren().addAll(
            new Label("old password"), oldPasswordField,
            new Label("new password"), newPasswordField,
            new Label("confirm new password"), confirmPasswordField,
            new Label("captcha"), captchaField,
            captchaLabel
    );

    // Cài đặt nội dung dialog
    dialog.getDialogPane().setContent(dialogContent);

        // Hiển thị Dialog và chờ người dùng đóng lại
        dialog.showAndWait();
    }

    void update() {
        user = UserDao.getInstance().selectByUserName(PreferencesHelper.getUsername());
        username_field.setText(user.getUserName());
        email_field.setText(user.getEmail());
        phone_field.setText(user.getPhone());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }

}
package oop.moneymanager.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

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
    public void setButtonSubmit(ActionEvent event) {
        String username = username_field.getText();
        String email = email_field.getText();
        UserModel user = UserDao.getInstance().selectByUserName(PreferencesHelper.getUsername());
        int ok = 1;
        if (!username.isEmpty() && username.equals(user.getUserName()) && !email.isEmpty() && email.equals(user.getEmail()) ) {
            showInputDialog("Quản lí thông tin của bạn tốt hơn!",user.getPassWord(),ok);
        }
        else {
            ok = 0;
            showInputDialog("Nhập sai thông tin người dùng","vui lòng nhập lại thông tin của bạn",ok);
        }

    }
    public  void setButtonCancel(ActionEvent event) throws IOException {
    SwitchSceneController controller = new SwitchSceneController();
    controller.switchToLoginScreen(event);
    }
    private void showInputDialog(String title, String password,int ok) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);

        // Tạo giao diện bên trong Dialog
        VBox dialogContent = new VBox(50);
        dialogContent.setPadding(new Insets(50));

        Label newpasswordLabel = new Label();
        if(ok == 1)
        {
            newpasswordLabel.setText("Mật khẩu của bạn là: " + password);
        }
        else
        {
            newpasswordLabel.setText(password);
        }



        ButtonType confirmButtonType = new ButtonType("Xác nhận");
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CLOSE);

        // Nút "Xác nhận" xử lý sự kiện
        dialog.getDialogPane().lookupButton(confirmButtonType).setOnMouseEntered(event -> {

        });

        // Thêm các phần tử vào VBox
        dialogContent.getChildren().addAll(newpasswordLabel);

        // Đặt VBox vào Dialog
        dialog.getDialogPane().setContent(dialogContent);

        // Hiển thị Dialog và chờ người dùng đóng lại
        dialog.showAndWait();
    }


}

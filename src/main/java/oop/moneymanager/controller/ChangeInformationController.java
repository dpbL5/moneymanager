package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeInformationController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private UserModel user;

    public ChangeInformationController() {
    }

    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/ChangeInfo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    @FXML
    public void handleSaveButton(ActionEvent event) throws IOException, SQLException {
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Kiểm tra dữ liệu hợp lệ
        if (email.isEmpty() || phone.isEmpty()) {
            System.out.println("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Cập nhật đối tượng UserModel
        user.setEmail(email);
        user.setPhone(phone);
        int row = UserDao.getInstance().change(user);
        System.out.println(row);
        if (row == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Đã cập nhật thành công!");
            alert.showAndWait();
        }
        SwitchSceneController controller = new SwitchSceneController();
        controller.switchToMainScreen(event);
    }
    @FXML
    public void handleCancelButton(ActionEvent event) throws IOException {
        SwitchSceneController controller = new SwitchSceneController();
        controller.switchToAccountInfo(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserDao.getInstance().selectByUserName(PreferencesHelper.getUsername());
    }
}

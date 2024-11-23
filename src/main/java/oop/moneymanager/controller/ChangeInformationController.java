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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.show();
            return;
        }
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(email.matches(EMAIL_REGEX)==false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert:");
            alert.setHeaderText(null);
            alert.setContentText("Please enter correct email format\n" +
                    "" +"Example: baodeptrai@gmail.com");
            alert.show();
            return;
        }

        // Cập nhật đối tượng UserModel

        boolean checkuser = UserDao.getInstance().selectByEmail(email);
        boolean checkphone = UserDao.getInstance().selectByPhone(phone);
        if (checkuser == false && checkphone == false) {
            user.setEmail(email);
            user.setPhone(phone);
            int row = UserDao.getInstance().change(user);
            System.out.println(row);
            if (row > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Update successfully!");
                alert.showAndWait();
                SwitchSceneController controller = new SwitchSceneController();
                controller.switchToMainScreen(event);
            }
        }
        else if(checkuser == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Email already in use, enter another email");
            alert.showAndWait();
        }
        else if(checkphone == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Phone number already in use, enter another phone number");
            alert.showAndWait();
        }



    }
    @FXML
    public void handleCancelButton(ActionEvent event) throws IOException {
        SwitchSceneController switchSceneController = new SwitchSceneController();
        switchSceneController.switchToMainScreen(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserDao.getInstance().selectByUserName(PreferencesHelper.getUsername());
    }
}

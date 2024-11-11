package oop.moneymanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class AccountInfoController {

    @FXML
    // Nút để thay đổi mật khẩu
    private Button change_PassWord;

    @FXML
    // Nút để đánh giá app (sẽ không cần làm backend cho phần này thêm vào cho có cho đỡ trống)
    private Button danhgia;

    @FXML
    // Nút để đăng xuất tài khoản
    private Button logout_Button;

    @FXML
    // Hàm xử lý việc thay đổi mật khẩu
    void changePassword(MouseEvent event) {

    }

    @FXML
    // Hàm sử lý việc thay đổi tài khoản
    void logOut(MouseEvent event) {

    }

}

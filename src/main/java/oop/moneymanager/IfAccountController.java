package oop.moneymanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class IfAccountController {

    @FXML
    // Nút để thay đổi mật khẩu
    private Button change_PassWord;

    @FXML
    // Nút để đánh giá app (sẽ không cần làm backend cho phần này thêm vào cho có cho đỡ trống)
    private Button danhgia;

    @FXML
    // Phần text hiển thị thông tin dữ liệu của tài khoản
    private TextField ifAccount;

    @FXML
    // Phần text hiển thị thông tin dữ liệu của email
    private TextField ifEmail;

    @FXML
    // Phần text hiển thị thông tin của SDT
    private TextField ifSdt;

    @FXML
    // Phần text hiển thị thông tin stk ngân hàng
    private TextField ifStk;

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

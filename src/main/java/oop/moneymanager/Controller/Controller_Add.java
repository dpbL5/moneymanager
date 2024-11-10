package oop.moneymanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Controller_Add {

    @FXML
    private Button Button_Chi;

    @FXML
    private Button Button_Thu;

    @FXML
    private Button Next;

    @FXML
    private Button SaveData;

    @FXML
    private AnchorPane T;

    @FXML
    private Text textfield;

    @FXML
    // Hàm income đại diện Nút "Thu" click sẽ hiển thị lên textField sẽ chuyển sang mục "Thu"
    void income(MouseEvent event) {
        textfield.setText("Thu");
    }

    @FXML
    // Hàm nextAdd đại diện cho Nút "Tiếp tục" click sẽ hiển thị lên 1 phần nhập mới
    void nextAdd(MouseEvent event) {
    }

    @FXML
    // Hàm pay đại diện cho Nút "Thu" click sẽ hiển thị lên textField sẽ chuyển sang mục "Chi"
    void pay(MouseEvent event) {
        textfield.setText("Chi");
    }

    @FXML
    // Hàm save đại diện cho Nút "save" click vào sẽ lưu dữ liệu người dùng nhập vào
    void save(MouseEvent event) {

    }

}

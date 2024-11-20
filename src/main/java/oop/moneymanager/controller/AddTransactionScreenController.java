package oop.moneymanager.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;


public class AddTransactionScreenController implements Initializable {

    @FXML
    private ChoiceBox<String> category_choice;
    @FXML
    private ChoiceBox<String> type_choice;
    @FXML
    private TextField amount;
    @FXML
    private TextField note;
    @FXML
    private ChoiceBox<String> kind_choice;
    @FXML
    private DatePicker date_choice;
    @FXML
    private Button addCategoryButton;
    public Scene setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/AddTransactionView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    public void bttsave(ActionEvent event) throws IOException, SQLException {
        String category = category_choice.getValue();
        String type = type_choice.getValue();
        String amount = this.amount.getText();
        double money;
        String note = this.note.getText();
        String kind = this.kind_choice.getValue();
        LocalDate selectedDate = date_choice.getValue(); // Lấy giá trị LocalDate
        if(category == null) {
            aler_information("bạn chưa chọn category cho số tiền của mình","háy nhấp vào category để thêm");
            return;
        }
        if(type == null) {
            aler_information("bạn chưa chọn type cho giao dịch của mình","hãy nhấp vào type để thêm");
            return;
        }
        try{
            money = Double.parseDouble(amount);
        }
        catch (Exception e) {
            aler_information("bạn chưa nhập hoặc đã nhập sai định dạng số", "vui lòng nhập lại");
            return;
        }
        if(note == null || note.trim().isEmpty()) {
            aler_information("bạn chưa nhập lưu ý cho giao dịch này","ban nên note lai cho giao dịch của mình để tránh nhầm lẫn");
            return;
        }
        if(kind == null || kind.trim().isEmpty()) {
            aler_information("bạn chưa chọn phương thức cho giao dịch  ","vui lòng chọn lại");
            return;
        }
        if (selectedDate == null) {
            aler_information("bạn chưa chọn ngày cho giao dịch này","hãy chọn ngày để quản lý tối ưu hơn");
            return;
        }
        String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Date: " + formattedDate);
        TransactionModel transaction = new TransactionModel(
            3, PreferencesHelper.getUsername(),category,money,note,selectedDate, 
            TransactionModel.TransactionType.valueOf(type),
            TransactionModel.TransactionKind.valueOf(kind));
        
        System.out.println(transaction.toString());
        try {
            int row = TransactionDao.getInstance().insert(transaction);
            System.out.println(row);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        SwitchSceneController switchSceneController = new SwitchSceneController();
        switchSceneController.switchToMainScreen(event);

    }
    public void bttcontinue(ActionEvent event) throws IOException {
        String category = category_choice.getValue();
        String type = type_choice.getValue();
        String amount = this.amount.getText();
        double money;
        String note = this.note.getText();
        String kind = this.kind_choice.getValue();
        LocalDate selectedDate = date_choice.getValue(); // Lấy giá trị LocalDate
        if(category == null) {
            aler_information("bạn chưa chọn category cho số tiền của mình","háy nhấp vào category để thêm");
            return;
        }
        if(type == null) {
            aler_information("bạn chưa chọn type cho giao dịch của mình","hãy nhấp vào type để thêm");
            return;
        }
        try{
            money = Double.parseDouble(amount);
        }
        catch (Exception e) {
            aler_information("bạn chưa nhập hoặc đã nhập sai định dạng số", "vui lòng nhập lại");
            return;
        }
        if(note == null || note.trim().isEmpty()) {
            aler_information("bạn chưa nhập lưu ý cho giao dịch này","ban nên note lai cho giao dịch của mình để tránh nhầm lẫn");
            return;
        }
        if(kind == null || kind.trim().isEmpty()) {
            aler_information("bạn chưa chọn phương thức cho giao dịch  ","vui lòng chọn lại");
            return;
        }
        if (selectedDate == null) {
            aler_information("bạn chưa chọn ngày cho giao dịch này","hãy chọn ngày để quản lý tối ưu hơn");
            return;
        }

        TransactionModel transaction = new TransactionModel(3,PreferencesHelper.getUsername(),category,money,note,selectedDate, TransactionModel.TransactionType.valueOf(type), TransactionModel.TransactionKind.valueOf(kind));
        System.out.println(transaction.toString());
        try {
            int row = TransactionDao.getInstance().insert(transaction);
            System.out.println(row);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        category_choice.setValue(null);
        type_choice.setValue(null);
        kind_choice.setValue(null);

        // Reset TextField fields
        this.amount.clear();
        this.note.clear();

        // Reset DatePicker field
        date_choice.setValue(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatecategory();
        generatetype();
        generatekind();

    }
    public void generatecategory()
    {
        // Thêm danh mục mặc định
        category_choice.getItems().addAll(
            PreferencesHelper.getSavedCategory()
        );

        addCategoryButton.setVisible(false);

        // Layout gốc của ChoiceBox
        Parent parent = category_choice.getParent();
        if (parent instanceof VBox) {
            VBox layout = (VBox) parent;
            layout.getChildren().add(addCategoryButton);
        }

        // Xử lý sự kiện
        category_choice.setOnMouseClicked(event -> addCategoryButton.setVisible(true));
        category_choice.setOnAction(event -> addCategoryButton.setVisible(false));
        addCategoryButton.setOnAction(event -> {
            // Hộp thoại nhập
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add New Category");
            dialog.setHeaderText("Enter the new category:");
            dialog.setContentText("Category:");

            // Nhận giá trị từ người dùng
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newCategory -> {
                if (!newCategory.trim().isEmpty() && !category_choice.getItems().contains(newCategory)) {
                    category_choice.getItems().add(newCategory);
                    PreferencesHelper.getSavedCategory().add(newCategory);
                    category_choice.setValue(newCategory);
                }
            });
        });
    }
    public void generatetype()
    {
        type_choice.getItems().addAll("INCOME", "EXPENSE");
    }
    public void generatekind()
    {
        kind_choice.getItems().addAll("CASH", "CREDIT_CARD", "BANK_ACCOUNT");
    }
    public void aler_information(String header,String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}

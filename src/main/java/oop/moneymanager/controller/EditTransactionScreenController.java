package oop.moneymanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;
import oop.moneymanager.model.TransactionModel.TransactionKind;

public class EditTransactionScreenController {

    private TransactionModel tempTransaction;

    @FXML
    private TextField amountField;

    @FXML
    private ChoiceBox<String> categoryBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> kindBox;

    @FXML
    private TextArea noteArea;

    @FXML
    private ChoiceBox<String> typeBox;
    
    
    private FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/oop/moneymanager/view/EditTransactionScreen.fxml")
    );

    public EditTransactionScreenController(TransactionModel transaction) {
        loader.setController(this);
        try {
            loader.load();
            setTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Parent getRoot() {
        return loader.getRoot();
    }

    public void setTransaction(TransactionModel transaction) {
        // initialize choice box
        typeBox.getItems().addAll(
            TransactionModel.TransactionType.EXPENSE.toString(),
            TransactionModel.TransactionType.INCOME.toString()
        );

        PreferencesHelper.getSavedCategory().forEach(category -> {
            categoryBox.getItems().add(category);
        });

        kindBox.getItems().addAll(
            TransactionKind.BANK_ACCOUNT.toString(), 
            TransactionKind.CASH.toString(),
            TransactionKind.CREDIT_CARD.toString()
        );

        tempTransaction = transaction;
        amountField.setText(String.valueOf(transaction.getAmount()));
        categoryBox.setValue(transaction.getCategory());
        datePicker.setValue(transaction.getDate());

        /*
         * Tạo converter để chuyển đổi LocalDate thành String và ngược lại
         * Định dạng ngày tháng là dd/MM/yyyy
         */
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            // Lấy ngày tháng từ LocalDate và chuyển thành String
            @Override
            public String toString(LocalDate date) {
                return date != null ? formatter.format(date) : "";
            }

            // Lấy String và chuyển thành LocalDate
            @Override
            public LocalDate fromString(String string) {
                return string != null && !string.isEmpty() ? LocalDate.parse(string, formatter) : null;
            }
        });

        kindBox.setValue(transaction.getKind().toString());
        noteArea.setText(transaction.getNote());
        typeBox.setValue(transaction.getType().toString());
    }

    @FXML
    void cancelHandle(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void saveHandle(ActionEvent event) {
        TransactionDao transactionDao = new TransactionDao();
        TransactionModel transaction = new TransactionModel(
            tempTransaction.getId(), 
            PreferencesHelper.getUsername(), 
            categoryBox.getValue(), 
            Double.parseDouble(amountField.getText()), 
            noteArea.getText(), 
            datePicker.getValue(), 
            TransactionModel.TransactionType.valueOf(typeBox.getValue()), 
            TransactionModel.TransactionKind.valueOf(kindBox.getValue())
        );
        int result = transactionDao.update(transaction);
        
        // Check if userinput is valid
        if (result == 0) {
            Dialog dialog = new Dialog();
            dialog.setContentText("Invalid input");
            dialog.setTitle("Error");
            dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.OK);
            dialog.showAndWait();
            return;
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void deleteHandle(ActionEvent event) {
        TransactionDao transactionDao = new TransactionDao();

        Dialog dialog = new Dialog();
        dialog.setContentText("Are you sure you want to delete this transaction?");
        dialog.setTitle("Delete Transaction");
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.YES);
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.NO);

        // if user click no, do nothing
        if (dialog.showAndWait().get() == javafx.scene.control.ButtonType.NO) {
            return;
        } else {
            // if user click yes, delete transaction
            transactionDao.delete(tempTransaction);
            System.out.println("Delete transaction");
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}

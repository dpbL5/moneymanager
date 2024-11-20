package oop.moneymanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;
import oop.moneymanager.model.TransactionModel.TransactionKind;

public class EditTransactionScreenController {

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
            System.out.println(category);
            categoryBox.getItems().add(category);
        });

        kindBox.getItems().addAll(
            TransactionKind.BANK_ACCOUNT.toString(), 
            TransactionKind.CASH.toString(),
            TransactionKind.CREDIT_CARD.toString()
        );

        amountField.setText(String.valueOf(transaction.getAmount()));
        categoryBox.setValue(transaction.getCategory());
        datePicker.setValue(transaction.getDate());
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
        // TransactionModel transaction = new TransactionModel(
        //     Integer.parseInt(amountField.getText()),
        //     categoryBox.getValue(),
        //     datePicker.getValue(),
        //     TransactionModel.TransactionKind.valueOf(kindBox.getValue()),
        //     noteArea.getText(),
        //     TransactionModel..valueOf(typeBox.getValue())
        // );
        // TransactionDao.update(transaction);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}

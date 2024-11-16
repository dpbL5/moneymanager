package oop.moneymanager.controller;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oop.moneymanager.model.TransactionModel;

public class TransactionCellController extends ListCell<TransactionModel> {

    @FXML
    private Label amountLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label noteLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label fromAccount;

    private FXMLLoader mLLoader;

    

    @Override
    protected void updateItem(TransactionModel transaction, boolean empty) {
        super.updateItem(transaction, empty);

        if (empty || transaction == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                // Load the fxml file
                mLLoader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/TransactionCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (Exception e) { // IOException | NullPointerException
                    e.printStackTrace();
                }
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            
            System.out.println(transaction.toString());

            timeLabel.setText(transaction.getTransactionDateTime().toLocalTime().format(timeFormatter).toString());
            dateLabel.setText(transaction.getTransactionDateTime().toLocalDate().format(formatter).toString());
            categoryLabel.setText(transaction.getCategory().toString());
            noteLabel.setText(transaction.getNote().toString());
            amountLabel.setText(String.valueOf(transaction.getAmount()));
            fromAccount.setText(transaction.getFromAccount().toString());

            setText(null);
            setGraphic(anchorPane);
        }
    }

}


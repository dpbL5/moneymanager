package oop.moneymanager.controller;

import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
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

    public TransactionCellController() {
        this.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/moneymanager/view/EditTransactionScreen.fxml"));
            try {
                stage.setScene(new javafx.scene.Scene(loader.load()));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

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

            timeLabel.setText(transaction.getDate().toLocalTime().format(timeFormatter).toString());
            dateLabel.setText(transaction.getDate().toLocalDate().format(formatter).toString());
            categoryLabel.setText(transaction.getCategory().toString());
            noteLabel.setText(transaction.getNote().toString());

            DecimalFormat numberFormat = new DecimalFormat("#,###.##");
            amountLabel.setText(numberFormat.format(transaction.getAmount()));
            fromAccount.setText(transaction.getKind().toString());

            setText(null);
            setGraphic(anchorPane);
        }
    }

}


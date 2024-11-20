package oop.moneymanager.controller;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oop.moneymanager.model.TransactionModel;

public class TransactionCellController extends ListCell<TransactionModel> {

    TransactionModel tempdata;

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
            if (tempdata == null) {
                System.out.println("Blank cell clicked");
                return;
            }

            try {
                EditTransactionScreenController controller = new EditTransactionScreenController(tempdata);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(controller.getRoot()));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void updateItem(TransactionModel transaction, boolean empty) {
        super.updateItem(transaction, empty);

        // luu lai data cua transaction
        tempdata = transaction;

        if (empty || transaction == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                // Load the fxml file
                mLLoader = new FXMLLoader(
                    getClass().getResource("/oop/moneymanager/view/TransactionCell.fxml")
                );
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (Exception e) { // IOException | NullPointerException
                    e.printStackTrace();
                }
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            // System.out.println(transaction.toString());
            dateLabel.setText(transaction.getDate().format(formatter).toString());
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


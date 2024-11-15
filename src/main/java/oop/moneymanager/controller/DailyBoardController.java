package oop.moneymanager.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import oop.moneymanager.model.TransactionModel;

public class DailyBoardController implements Initializable{

    // Hien thong tin cua 1 thang

    @FXML
    private ListView<TransactionModel> transListView;

    private ObservableList<TransactionModel> transObsList;

    public DailyBoardController() {
        transObsList = FXCollections.observableArrayList();
        
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            transObsList.add(
                new TransactionModel(
                    Integer.toString(i),
                    "user1",
                    LocalDateTime.now(),
                    TransactionModel.TransactionType.INCOME,
                    1000L+i*random.nextInt(100_000_000),
                    "Salary",
                    "Salary for this month - Salary for this month - Salary for this month - Salary for this month ",
                    "Ngân hàng MBBANK"
                )
            );
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        transListView.setItems(transObsList);
        transListView.setCellFactory(transListView -> new TransactionCellController());
    }

    

}

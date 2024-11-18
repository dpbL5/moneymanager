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
        
        // Fake data for testing
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            transObsList.add(new TransactionModel(
                i,
                "User " + i,
                "Category " + i,
                rand.nextDouble() * 1000,
                "Note " + i,
                LocalDateTime.now(),
                TransactionModel.TransactionType.EXPENSE,
                TransactionModel.TransactionKind.CASH
            ));
        }
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        transListView.setItems(transObsList);
        transListView.setCellFactory(transListView -> new TransactionCellController());
    }

    

}

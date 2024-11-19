package oop.moneymanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.model.TransactionModel;
import oop.moneymanager.service.DataManipulation;

public class DailyBoardController implements Initializable{

    // Hien thong tin cua 1 thang

    @FXML
    private ListView<TransactionModel> transListView;

    private ObservableList<TransactionModel> transObsList;

    public DailyBoardController() {
        transObsList = FXCollections.observableArrayList();

        DataManipulation retriever = new DataManipulation();
        retriever.getTransactionModels(PreferencesHelper.getUsername()).forEach(trans -> {
            transObsList.add(trans);
        });
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        transListView.setItems(transObsList);
        transListView.setCellFactory(transListView -> new TransactionCellController());
    }

    

}

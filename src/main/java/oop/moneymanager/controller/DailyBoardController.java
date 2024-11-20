package oop.moneymanager.controller;

import java.net.URL;
import java.util.ArrayList;
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

        ArrayList<String> defaultCategories = new ArrayList<>();
        defaultCategories.add("Food");
        defaultCategories.add("Transport");
        defaultCategories.add("Entertainment");
        defaultCategories.add("Shopping");
        defaultCategories.add("Utilities");
        defaultCategories.add("Healthcare");
        defaultCategories.add("Education");
        defaultCategories.add("Travel");
        defaultCategories.add("Savings");
        defaultCategories.add("Investments");
        defaultCategories.add("Gifts");
        defaultCategories.add("Charity");
        defaultCategories.add("Insurance");
        defaultCategories.add("Taxes");
        PreferencesHelper.getSavedCategory().addAll(defaultCategories);

        transListView.setItems(transObsList);
        transListView.setCellFactory(transListView -> new TransactionCellController());
    }

    

}

package oop.moneymanager.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;

public class DailyBoardController implements Initializable{

    private LocalDate currentMYear = LocalDate.now();

    @FXML
    private ListView<TransactionModel> transListView;

    @FXML
    private Button nextMYear;

    @FXML
    private Button previousMYear;

    @FXML
    private Label mYearLabel;

    private ObservableList<TransactionModel> transObsList;

    public DailyBoardController() {
        transObsList = FXCollections.observableArrayList();
        update();
    }

    /*
     * This method is used to update the transaction list view
     * 1. Clear the current transaction list
     * 2. Get all transactions from the database of the current user
     * 3. Add all transactions to the transaction list
     */
    public void update() {

        transObsList.clear();

        TransactionDao transactionDao = new TransactionDao();
        String condition    = "username=\'" + PreferencesHelper.getUsername() + "\'" 
                            + " AND YEAR(transaction_date)=" + currentMYear.getYear()
                            + " AND MONTH(transaction_date)=" + currentMYear.getMonth().getValue();

        ArrayList<TransactionModel> transactions = transactionDao.selectByCondition(condition);

        for (TransactionModel transaction : transactions) {
            transObsList.add(transaction);
        }
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        
        mYearLabel.setText(currentMYear.format(formatter));
        
        previousMYear.setOnMouseClicked(event -> {
            currentMYear = currentMYear.minusMonths(1);
            mYearLabel.setText(currentMYear.format(formatter));
            update();
        });

        nextMYear.setOnMouseClicked(event -> {
            currentMYear = currentMYear.plusMonths(1);
            mYearLabel.setText(currentMYear.format(formatter));
            update();
        });


        transListView.setItems(transObsList);
        transListView.setCellFactory(transListView -> new TransactionCellController());
        
        System.out.println("-< DailyBoardController Initialized >-");
    }

}

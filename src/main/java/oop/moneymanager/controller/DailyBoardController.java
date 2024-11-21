package oop.moneymanager.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private ChoiceBox<String> monthChoice;

    @FXML
    private TextField yearField;

    @FXML
    private Button thisMonthButton;

    private ObservableList<TransactionModel> transObsList;

    public DailyBoardController() {
        transObsList = FXCollections.observableArrayList();
        update();
    }

    public void update() {
        // clear list truoc
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
        
        // Set the month choice box
        monthChoice.setItems(FXCollections.observableArrayList(
            Month.JANUARY.toString(),
            Month.FEBRUARY.toString(),
            Month.MARCH.toString(),
            Month.APRIL.toString(),
            Month.MAY.toString(),
            Month.JUNE.toString(),
            Month.JULY.toString(),
            Month.AUGUST.toString(),
            Month.SEPTEMBER.toString(),
            Month.OCTOBER.toString(),
            Month.NOVEMBER.toString(),
            Month.DECEMBER.toString()
        ));

        monthChoice.setValue(currentMYear.getMonth().toString());

        // Set the current month and year
        yearField.setText(Integer.toString(currentMYear.getYear()));

        // Set the previous and next month button
        previousMYear.setOnMouseClicked(event -> {
            currentMYear = currentMYear.minusMonths(1);
            monthChoice.setValue(currentMYear.getMonth().toString());
            yearField.setText(Integer.toString(currentMYear.getYear()));
            update();
        });

        // Set the previous and next month button
        nextMYear.setOnMouseClicked(event -> {
            currentMYear = currentMYear.plusMonths(1);
            monthChoice.setValue(currentMYear.getMonth().toString());
            yearField.setText(Integer.toString(currentMYear.getYear()));
            update();
        });

        // Update the month and year khi the user changes the month
        monthChoice.setOnAction(event -> {
            currentMYear = LocalDate.of(currentMYear.getYear(), Month.valueOf(monthChoice.getValue()), 1);
            update();
        });

        yearField.setOnAction(event -> {
            currentMYear = LocalDate.of(Integer.parseInt(yearField.getText()), currentMYear.getMonth(), 1);
            update();
        });

        thisMonthButton.setOnMouseClicked(event -> {
            currentMYear = LocalDate.now();
            monthChoice.setValue(currentMYear.getMonth().toString());
            yearField.setText(Integer.toString(currentMYear.getYear()));
            update();
        });

        // Set the transaction list view and cell factory
        transListView.setItems(transObsList);
        transListView.setCellFactory(transListView -> new TransactionCellController(this));
        
        System.out.println("-< DailyBoardController Initialized >-");
    }

}

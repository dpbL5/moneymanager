package oop.moneymanager.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;
import oop.moneymanager.service.ExportFileHandle;

public class ExportToExcelController {

    @FXML
    private Button lastMonth;

    @FXML
    private Button lastYear;

    @FXML
    private Button thisMonth;

    @FXML
    private Button thisYear;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

    @FXML
    public void initialize() {
        thisMonth.setOnAction(e -> {
                LocalDate date = LocalDate.now();
                System.out.println("Export this month:" + formatter.format(date));
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose directory to save file");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName("MonthlyReport_" + formatter.format(date));
                
                try {
                    ExportFileHandle.exportMonthly(directoryChooser.showDialog(null) + fileChooser.getInitialFileName(), date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        );

        lastMonth.setOnAction(e -> {
                LocalDate date = LocalDate.now().minusMonths(1);
                System.out.println("Export last month:" + formatter.format(date));
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose directory to save file");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName("MonthlyReport_" + formatter.format(date));
                
                try {
                    ExportFileHandle.exportMonthly(directoryChooser.showDialog(null) + fileChooser.getInitialFileName(), date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        );

        thisYear.setOnAction(e -> {
                LocalDate date = LocalDate.now();
                System.out.println("Export this year:" + date.getYear());
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose directory to save file");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName("YearlyReport_" + date.getYear());
                
                try {
                    ExportFileHandle.exportAnnually(directoryChooser.showDialog(null) + fileChooser.getInitialFileName(), date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        );

        lastYear.setOnAction(e -> {
                LocalDate date = LocalDate.now().minusYears(1);
                System.out.println("Export last year:" + date.getYear());
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose directory to save file");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName("YearlyReport_" + date.getYear());
                
                try {
                    ExportFileHandle.exportAnnually(directoryChooser.showDialog(null) + fileChooser.getInitialFileName(), date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        );
    }
}


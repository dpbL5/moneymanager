package oop.moneymanager.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oop.moneymanager.service.ExportFileHandler;

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

    void export(LocalDate date, Boolean isMonthly) {
        Stage stage = (Stage) thisMonth.getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose directory to save file");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        File path = directoryChooser.showDialog(stage);
        try {
            if (path != null) {
                if (isMonthly) {                    
                    String name = "MonthlyReport_" + formatter.format(date);
                    ExportFileHandler.exportMonthly(path + name, date);
                } else {
                    String name = "AnnualReport_" + date.getYear();
                    ExportFileHandler.exportAnnually(path + name, date);
                } 
                System.out.println("Export: " + formatter.format(date));
                System.out.println("Exporting to: " + path);
            } else {
                System.out.println("No directory selected");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        thisMonth.setOnAction(e -> {
                LocalDate date = LocalDate.now();
                export(date, true);
            }
        );

        lastMonth.setOnAction(e -> {
                LocalDate date = LocalDate.now().minusMonths(1);
                export(date, true);
            }
        );

        thisYear.setOnAction(e -> {
                LocalDate date = LocalDate.now();
                export(date, false);
            }
        );

        lastYear.setOnAction(e -> {
                LocalDate date = LocalDate.now().minusYears(1);
                export(date, false);
            }
        );
    }
}


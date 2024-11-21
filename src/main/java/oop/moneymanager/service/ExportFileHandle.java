package oop.moneymanager.service;

import java.time.LocalDate;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportFileHandle {

    private static void writeHeader() {
        // Write header
        
    }; 

    public void exportMonthly(String fileName, Boolean isLastMonth) {
        Workbook workbook = new XSSFWorkbook();
        // Write header
        writeHeader();

    }

    public void exportAnnually(String fileName) {
        // Export file

    }
}

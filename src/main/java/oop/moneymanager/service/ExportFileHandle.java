package oop.moneymanager.service;

import java.time.LocalDate;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportFileHandle {

    public void exportMonthly(String fileName, Boolean isLastMonth) {
        // Export file

    }

    public void exportAnnually(String fileName, Boolean isLastYear) {
        // Export file
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Transactions");

        // Create a Row
        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        // Create cells
        headerRow.createCell(0).setCellValue("Date");
        headerRow.createCell(1).setCellValue("Amount");
        headerRow.createCell(2).setCellValue("Category");
        headerRow.createCell(3).setCellValue("Note");
        
    }
}

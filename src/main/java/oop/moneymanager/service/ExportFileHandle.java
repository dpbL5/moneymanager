package oop.moneymanager.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;


public class ExportFileHandle {

    private static String[] header = { "Date", "Kind", "Category", "Amount", "Income/Expense", "Note"};
    
    public static void exportMonthly(String fileName, LocalDate date) 
        throws IOException{
        
        ArrayList<TransactionModel> list = new ArrayList<>();
                list = TransactionDao.getInstance().selectByCondition(
                    "MONTH(transaction_date) = " + date.getMonthValue() +
                    " AND YEAR(transaction_date) = " + date.getYear());

        // Export file
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Monthly Report");
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        

        XSSFRow headRow = sheet.createRow(0);

        for (int i = 0; i < header.length; i++) {
            XSSFCell cell = headRow.createCell(i);
            cell.setCellValue(header[i]);
            cell.getCellStyle().setFont(headerFont);
        }

        // Đặt cellstyle
        XSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));

        int rowNum = 1;
        for (TransactionModel transaction : list) {
            XSSFRow row = sheet.createRow(rowNum++);

            XSSFCell dateCell = row.createCell(0);
            dateCell.setCellValue(transaction.getDate().toString());
            dateCell.setCellStyle(dateCellStyle);
            // row.createCell(0).setCellValue(transaction.getDate().toString());
            row.createCell(1).setCellValue(transaction.getKind().toString());
            row.createCell(2).setCellValue(transaction.getCategory());
            row.createCell(3).setCellValue(transaction.getAmount());
            row.createCell(4).setCellValue(transaction.getType().toString());
            row.createCell(5).setCellValue(transaction.getNote());
        }

        FileOutputStream fileOut = new FileOutputStream(fileName + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    public static void exportAnnually(String fileName, LocalDate date) 
        throws IOException{

            ArrayList<TransactionModel> list = new ArrayList<>();
            list = TransactionDao.getInstance().selectByCondition(
                "YEAR(transaction_date) = " + date.getYear());

        // Export file
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Annually Report" + date.getYear());

        XSSFRow headRow = sheet.createRow(0);
        
        for (int i = 0; i < header.length; i++) {
            XSSFCell cell = headRow.createCell(i);
            cell.setCellValue(header[i]);
        }

        // Đặt cellstyle
        XSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));

        int rowNum = 1;

        for (TransactionModel transaction : list) {
            XSSFRow row = sheet.createRow(rowNum++);

            XSSFCell dateCell = row.createCell(0);
            dateCell.setCellValue(transaction.getDate().toString());
            dateCell.setCellStyle(dateCellStyle);
            // row.createCell(0).setCellValue(transaction.getDate().toString());
            row.createCell(1).setCellValue(transaction.getKind().toString());
            row.createCell(2).setCellValue(transaction.getCategory());
            row.createCell(3).setCellValue(transaction.getAmount());
            row.createCell(4).setCellValue(transaction.getType().toString());
            row.createCell(5).setCellValue(transaction.getNote());
        }

        FileOutputStream fileOut = new FileOutputStream
            (fileName + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}

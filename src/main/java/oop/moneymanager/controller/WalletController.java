package oop.moneymanager.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import oop.moneymanager.service.PreferencesHelper;
import oop.moneymanager.dao.BudgetDao;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.BudgetModel;
import oop.moneymanager.service.PreferencesHelper;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WalletController implements Initializable {
    @FXML
    public Label wallet_ex_cash_lbl;
    @FXML
    public Label wallet_ex_bank_lbl;
    @FXML
    public Label wallet_ex_credit_lbl;
    @FXML
    public Label wallet_in_cash_lbl;
    @FXML
    public Label wallet_in_bank_lbl;
    @FXML
    public Label wallet_in_credit_lbl;
    @FXML
    public Label wallet_income_lbl;
    @FXML
    public Label wallet_expense_lbl;
    @FXML
    public Label wallet_total_lbl;
    @FXML
    public Label message_lbl;
    @FXML
    public DatePicker start_date;
    @FXML
    public DatePicker end_date;
    public static LocalDate startDate;
    public static LocalDate endDate;
    @FXML
    public Button edit_limit_btn;
    @FXML
    public Label total_limit_lbl;
    @FXML
    public Label remaining_lbl;
    @FXML
    public ProgressBar percen_bar;
    @FXML
    public Label percen_lbl;
    @FXML
    public Button refresh_percen_btn;
    @FXML
    public TextField edit_text_fld;
    @FXML
    public Button cancel_edit_btn;
    @FXML
    public Button save_edit_btn;
    @FXML
    public Label limit_message_lbl;
    private static int Alertcount = 1;
    private TransactionDao transactionDao;
    private BudgetDao budgetDao;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionDao = new TransactionDao();

        budgetDao = new BudgetDao();

        String username = PreferencesHelper.getUsername();

        initTransactionLimit();

        // Gắn sự kiện cho DatePicker
        start_date.setOnAction(event -> loadWalletStatistics(username));
        end_date.setOnAction(event -> loadWalletStatistics(username));

        // Gán giá trị mặc định cho DatePicker
        start_date.setValue(LocalDate.now().minusDays(30));
        end_date.setValue(LocalDate.now());

        // Tải thống kê ban đầu
        loadWalletStatistics(username);
        loadTransactionLimit(username);
        handleLimitBtn(username);

    }
    public void initTransactionLimit() {
        cancel_edit_btn.setVisible(false);
        cancel_edit_btn.setManaged(false);
        save_edit_btn.setVisible(false);
        save_edit_btn.setManaged(false);
        edit_text_fld.setVisible(false);
        edit_text_fld.setManaged(false);

    }
    public void handleLimitBtn(String username) {
        // Gắn sự kiện cho nút edit
        edit_limit_btn.setOnAction(event -> showEditControls());

        // Gắn sự kiện cho nút save
        save_edit_btn.setOnAction(event -> {
            saveEditLimit(username);
            hideEditControls();
        });

        // Gắn sự kiện cho nút cancel
        cancel_edit_btn.setOnAction(event -> hideEditControls());
    }
    private void showEditControls() {
        cancel_edit_btn.setVisible(true);
        cancel_edit_btn.setManaged(true);
        save_edit_btn.setVisible(true);
        save_edit_btn.setManaged(true);
        edit_text_fld.setVisible(true);
        edit_text_fld.setManaged(true);
    }

    private void hideEditControls() {
        cancel_edit_btn.setVisible(false);
        cancel_edit_btn.setManaged(false);
        save_edit_btn.setVisible(false);
        save_edit_btn.setManaged(false);
        edit_text_fld.setVisible(false);
        edit_text_fld.setManaged(false);
    }

    private void saveEditLimit(String username) {
        try {
            double newLimit = Double.parseDouble(edit_text_fld.getText());

            if (newLimit <= 0) {
                message_lbl.setText("The transaction limit must be greater than 0. Please enter again.");
                total_limit_lbl.setText("INVALID");
                remaining_lbl.setText("INVALID");
                return;
            }

            // Lay thong tin tong han muc hien tai
            Map<String, Double> summary = budgetDao.getBudgetAndExpenseSummary(username);
            Double totalLimit = summary.get("total_limit");
            // Them moi neu chua ton tai TransactionLimit
            if (totalLimit == null || totalLimit == 0.0) {
                BudgetModel budgetModel = new BudgetModel();
                budgetModel.setUsername(username);
                budgetModel.setDate_init(LocalDate.now());
                budgetModel.setAmount(newLimit);

                budgetDao.insert(budgetModel);
                message_lbl.setText("Budget limit inserted successfully.");
            } else {
                //Cap nhat neu da ton tai
                budgetDao.update(new BudgetModel(username, newLimit));
                message_lbl.setText("Budget limit updated successfully.");
            }

            // Cập nhật lại giao diện
            loadTransactionLimit(username);
        } catch (NumberFormatException e) {
            message_lbl.setText("Invalid input. Please enter a valid number.");
        } catch (SQLException e) {
            e.printStackTrace();
            message_lbl.setText("An error occurred while updating the budget limit.");
        }
    }



    private void loadWalletStatistics(String username) {
        startDate = start_date.getValue();
        endDate = end_date.getValue();

        if (startDate.isAfter(endDate)) {
            message_lbl.setText("Start date must be earlier than end date.");
            return;
        }
        message_lbl.setText("");


        Map<String, Map<String, Double>> summary = transactionDao.getTransactionSummaryByKind(username, startDate, endDate);

        wallet_in_cash_lbl.setText(formatAmount(summary, "CASH", "INCOME"));
        wallet_in_bank_lbl.setText(formatAmount(summary, "BANK_ACCOUNT", "INCOME"));
        wallet_in_credit_lbl.setText(formatAmount(summary, "CREDIT_CARD", "INCOME"));

        wallet_ex_cash_lbl.setText(formatAmount(summary, "CASH", "EXPENSE"));
        wallet_ex_bank_lbl.setText(formatAmount(summary, "BANK_ACCOUNT", "EXPENSE"));
        wallet_ex_credit_lbl.setText(formatAmount(summary, "CREDIT_CARD", "EXPENSE"));


        double totalIncome = getTotalAmount(summary, "INCOME");
        double totalExpense = getTotalAmount(summary, "EXPENSE");

        wallet_income_lbl.setText(String.format("%.2f $", totalIncome));
        wallet_expense_lbl.setText(String.format("%.2f $", totalExpense));
        wallet_total_lbl.setText(String.format("%.2f $", totalIncome - totalExpense));
    }

    private String formatAmount(Map<String, Map<String, Double>> summary, String kind, String type) {
        if (summary.containsKey(kind) && summary.get(kind).containsKey(type)) {
            return String.format("%.2f $", summary.get(kind).get(type));
        }
        return "0.00 $";
    }

    private double getTotalAmount(Map<String, Map<String, Double>> summary, String type) {
        return summary.values().stream()
                .filter(map -> map.containsKey(type))
                .mapToDouble(map -> map.get(type))
                .sum();
    }

    private void loadTransactionLimit(String username) {
        Map<String, Double> summary = budgetDao.getBudgetAndExpenseSummary(username);

        Double totalLimit = summary.get("total_limit");
        double totalExpense = summary.getOrDefault("total_expense", 0.0);
        double remaining = 0;
        if (totalLimit == null || totalLimit == 0.0) {
            total_limit_lbl.setText("NOT SET");
            remaining_lbl.setText("NOT SET");
            percen_bar.setProgress(100);
            percen_lbl.setText("Infinity");
            limit_message_lbl.setText("");
        } else {
            remaining = totalLimit - totalExpense;
            total_limit_lbl.setText(String.format("%.0f", totalLimit) + " $");
            remaining_lbl.setText(String.format("%.0f", remaining)+ " $");
            if(remaining <= 0 ) {
                if(Alertcount == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ALERT");
                    alert.setHeaderText(null);
                    alert.setContentText("LIMIT EXCEEDED");
                    alert.showAndWait();
                    Alertcount = 2;
                    percen_bar.setProgress(0);
                }
                limit_message_lbl.setText("LIMIT EXCEEDED");
                percen_bar.setProgress(0);
                percen_lbl.setText("0%");
                return;
            }
            limit_message_lbl.setText("");
            double percent = (remaining / totalLimit) * 100;
            percen_bar.setProgress(percent / 100);
            percen_lbl.setText(String.format("%.1f%%", percent) );
        }
    }

}


package oop.moneymanager.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import oop.moneymanager.dao.TransactionDao;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WalletController implements Initializable {

    @FXML
    public LineChart<String, Number> wallet_line_chart;
    @FXML
    public ChoiceBox<String> wallet_choice_box;
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

    private TransactionDao transactionDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionDao = new TransactionDao();

        // Thiết lập giá trị cho ChoiceBox
        ObservableList<String> choices = FXCollections.observableArrayList("Day", "Month", "Year");
        wallet_choice_box.setItems(choices);
        wallet_choice_box.setValue("Day"); // Giá trị mặc định

        // Gắn sự kiện thay đổi cho ChoiceBox
        wallet_choice_box.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateLineChart(newValue.toLowerCase(), "bao2");
        });

        // Nạp dữ liệu ban đầu
        loadWalletData("bao2");
        updateLineChart("day", "bao2");
    }

    // Nạp dữ liệu thu nhập, chi tiêu cho các nhãn hiển thị
    private void loadWalletData(String username) {
        Map<String, Map<String, Double>> summary = transactionDao.getTransactionSummaryByKind(username);

        wallet_in_cash_lbl.setText(formatAmount(summary, "CASH", "INCOME"));
        wallet_in_bank_lbl.setText(formatAmount(summary, "BANK_ACCOUNT", "INCOME"));
        wallet_in_credit_lbl.setText(formatAmount(summary, "CREDIT_CARD", "INCOME"));

        wallet_ex_cash_lbl.setText(formatAmount(summary, "CASH", "EXPENSE"));
        wallet_ex_bank_lbl.setText(formatAmount(summary, "BANK_ACCOUNT", "EXPENSE"));
        wallet_ex_credit_lbl.setText(formatAmount(summary, "CREDIT_CARD", "EXPENSE"));

        double totalIncome = getTotalAmount(summary, "INCOME");
        double totalExpense = getTotalAmount(summary, "EXPENSE");

        wallet_income_lbl.setText(String.format("%.2f", totalIncome));
        wallet_expense_lbl.setText(String.format("%.2f", totalExpense));
        wallet_total_lbl.setText(String.format("%.2f", totalIncome - totalExpense));
    }

    // Cập nhật dữ liệu LineChart theo khoảng thời gian và người dùng
    public void updateLineChart(String period, String username) {
        Map<String, Map<String, Double>> data = transactionDao.getDetailedTransactionSummaryByTime(username, period);
        wallet_line_chart.getData().clear();

        for (String kind : List.of("CASH", "BANK_ACCOUNT", "CREDIT_CARD")) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(kind);

            data.getOrDefault(kind, Map.of()).forEach((time, value) -> {
                series.getData().add(new XYChart.Data<>(time, value));
            });

            wallet_line_chart.getData().add(series);
        }
    }

    // Định dạng giá trị số để hiển thị
    private String formatAmount(Map<String, Map<String, Double>> summary, String kind, String type) {
        if (summary.containsKey(kind) && summary.get(kind).containsKey(type)) {
            return String.format("%.2f", summary.get(kind).get(type));
        }
        return "0.00";
    }

    // Tính tổng thu nhập hoặc chi tiêu
    private double getTotalAmount(Map<String, Map<String, Double>> summary, String type) {
        return summary.values().stream()
                .filter(map -> map.containsKey(type))
                .mapToDouble(map -> map.get(type))
                .sum();
    }
}


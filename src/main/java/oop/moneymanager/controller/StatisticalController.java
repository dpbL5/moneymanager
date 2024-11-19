package oop.moneymanager.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.DetailModel;
import oop.moneymanager.model.TransactionModel;
import oop.moneymanager.model.UserModel;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class StatisticalController implements Initializable {

    @FXML
    public Label messageLabel;
    public AnchorPane stat_income_pane;
    public AnchorPane stat_outcome_pane;
    public AnchorPane stat_total_pane;
    public AnchorPane total_header;
    @FXML
    public Label stat_income_lbl;
    @FXML
    public Label stat_out_lbl;
    @FXML
    public Label stat_total_lbl;
    @FXML
    public AnchorPane stat_details_pane;
    @FXML
    private TableView<DetailModel> stat_details_table;
    @FXML
    private TableColumn<DetailModel, String> categoryColumn;
    @FXML
    private TableColumn<DetailModel, Double> amountColumn;
    @FXML
    private TableColumn<DetailModel, String> percentageColumn;
    @FXML
    public ChoiceBox<String> stat_choice_box;
    @FXML
    private DatePicker sta_date_start;
    @FXML
    private DatePicker sta_date_end;
    @FXML
    private PieChart stat_in_pie_chart;
    @FXML
    private PieChart stat_out_pie_chart;

    private TransactionDao transactionDao;
    private UserModel userModel;
    private ObservableList<DetailModel> Incomedetails = FXCollections.observableArrayList();
    private ObservableList<DetailModel> Expensedetails = FXCollections.observableArrayList();

    public void settingPiechart(PieChart pieChart) {
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(false);
        pieChart.setStartAngle(90);
        pieChart.setAnimated(true);
        pieChart.setClockwise(false);
    }

    public void setStat_choice_box() {
        stat_choice_box.getItems().addAll("Income", "Expense");
        stat_choice_box.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateTableView(newValue);
            }
        });
        stat_choice_box.getSelectionModel().selectFirst(); // Mặc định chọn "Income"
    }

    private void updateTableView(String transactionType) {
        ObservableList<DetailModel> filteredDetails = FXCollections.observableArrayList();

        // Lọc dữ liệu theo loại giao dịch (Income hoặc Expense)
        if ("Income".equals(transactionType)) {
            filteredDetails.setAll(Incomedetails);  // Hiển thị chi tiết thu nhập
        } else if ("Expense".equals(transactionType)) {
            filteredDetails.setAll(Expensedetails);  // Hiển thị chi tiết chi tiêu
        }

        stat_details_table.setItems(filteredDetails);
        stat_details_table.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionDao = new TransactionDao();

        // Đặt giá trị mặc định cho DatePicker
        sta_date_end.setValue(LocalDate.now()); // Ngày kết thúc là ngày hiện tại
        sta_date_start.setValue(LocalDate.now().minusDays(30)); // Ngày bắt đầu là 30 ngày trước ngày hiện tại

        settingPiechart(stat_in_pie_chart);
        settingPiechart(stat_out_pie_chart);

        // Khởi tạo cột trong TableView
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        percentageColumn.setCellValueFactory(cellData -> cellData.getValue().percentageProperty());

        sta_date_start.setOnAction(event -> loadData());
        sta_date_end.setOnAction(event -> loadData());

        // Tải dữ liệu ban đầu với khoảng thời gian mặc định
        loadData();
        setStat_choice_box();
    }

    private void loadData() {
        LocalDate endDate = sta_date_end.getValue();
        LocalDate startDate = sta_date_start.getValue();

        if (endDate == null) {
            endDate = LocalDate.now();
        }
        if (startDate == null) {
            startDate = endDate.minusDays(30);
        }

        long daysBetween = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

        if (daysBetween > 30) {
            messageLabel.setText("Thời gian vượt quá 30 ngày, vui lòng chọn lại");
            stat_in_pie_chart.getData().clear();
            stat_out_pie_chart.getData().clear();
        } else {
            double totalIncome = 0;
            double totalExpense = 0;

            messageLabel.setText("");
            String username = "bao2";
            List<TransactionModel> transactions = transactionDao.selectByRangedateAndUsername(username, startDate, endDate);

            // Làm mới danh sách chi tiết
            Incomedetails.clear();
            Expensedetails.clear();

            stat_in_pie_chart.getData().clear();
            stat_out_pie_chart.getData().clear();

            Map<String, Double> incomeData = new HashMap<>();
            Map<String, Double> outcomeData = new HashMap<>();

            for (TransactionModel transaction : transactions) {
                if ("INCOME".equalsIgnoreCase(String.valueOf(transaction.getType()))) {
                    incomeData.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
                } else if ("EXPENSE".equalsIgnoreCase(String.valueOf(transaction.getType()))) {
                    outcomeData.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
                }
            }

            totalIncome = addDataToPieChart(stat_in_pie_chart, incomeData, "Income");
            totalExpense = addDataToPieChart(stat_out_pie_chart, outcomeData, "Expense");

            stat_income_lbl.setText(String.valueOf(totalIncome));
            stat_out_lbl.setText(String.valueOf(totalExpense * -1.0));
            stat_total_lbl.setText(String.valueOf(totalIncome - totalExpense));
        }
    }

    private double addDataToPieChart(PieChart pieChart, Map<String, Double> data, String transactionType) {
        double threshold = 5.0; // Ngưỡng để xác định các danh mục nhỏ
        double otherTotal = 0.0;
        double totalValue = data.values().stream().mapToDouble(Double::doubleValue).sum(); // Tổng giá trị để tính phần trăm

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            if (entry.getValue() > threshold) {
                // Tính phần trăm
                double percentage = (entry.getValue() / totalValue) * 100;
                String label = entry.getKey() + " (" + String.format("%.1f", percentage) + "%)";

                // Tạo PieChart.Data với tên chứa phần trăm
                PieChart.Data chartData = new PieChart.Data(label, entry.getValue());
                pieChart.getData().add(chartData);

                // Chỉ thêm vào details nếu phù hợp với transactionType
                if ("Income".equals(transactionType) && entry.getValue() > 0) {
                    Incomedetails.add(new DetailModel(entry.getKey(), entry.getValue(), String.format("%.1f%%", percentage)));
                } else if ("Expense".equals(transactionType)) {
                    Expensedetails.add(new DetailModel(entry.getKey(), entry.getValue(), String.format("%.1f%%", percentage)));
                }
            } else {
                otherTotal += entry.getValue();
            }
        }

        // Thêm danh mục "Khác" nếu có dữ liệu
        if (otherTotal > 0) {
            double otherPercentage = (otherTotal / totalValue) * 100;
            String otherLabel = "Khác (" + String.format("%.1f", otherPercentage) + "%)";
            PieChart.Data otherData = new PieChart.Data(otherLabel, otherTotal);
            pieChart.getData().add(otherData);

            if ("Income".equals(transactionType) && otherTotal > 0) {
                Incomedetails.add(new DetailModel("Khác", otherTotal, String.format("%.1f%%", otherPercentage)));
            } else if ("Expense".equals(transactionType)) {
                Expensedetails.add(new DetailModel("Khác", otherTotal, String.format("%.1f%%", otherPercentage)));
            }
        }

        return totalValue;
    }


}

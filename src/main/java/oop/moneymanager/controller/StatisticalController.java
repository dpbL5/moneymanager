package oop.moneymanager.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import oop.moneymanager.PreferencesHelper;
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
    public AnchorPane stat_expense_pane;
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
    private final ObservableList<DetailModel> Incomedetails = FXCollections.observableArrayList();
    private final ObservableList<DetailModel> Expensedetails = FXCollections.observableArrayList();
    private static LocalDate  startDate = LocalDate.now().minusDays(30);
    private static LocalDate endDate = LocalDate.now();

    public void settingPiechart(PieChart pieChart) {
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(false);
        pieChart.setStartAngle(90);
//        pieChart.setAnimated(true);
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
        sta_date_end.setValue(endDate); // Ngày kết thúc là ngày hiện tại
        sta_date_start.setValue(startDate); // Ngày bắt đầu là 30 ngày trước ngày hiện tại

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
        endDate = sta_date_end.getValue();
        startDate = sta_date_start.getValue();

        if (endDate == null) {
            endDate = LocalDate.now();
        }
        if (startDate == null) {
            startDate = endDate.minusDays(30);
        }
        if (startDate.isAfter(endDate)) {
            messageLabel.setText("The start date must be earlier than the end date. Please select again.");
            return; // Thoát khỏi hàm nếu ngày bắt đầu lớn hơn ngày kết thúc
        }

//        long daysBetween = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
//
//        if (daysBetween > 30) {
//            messageLabel.setText("The selected time range exceeds 30 days. Please select again!");
//            stat_in_pie_chart.getData().clear();
//            stat_out_pie_chart.getData().clear();
//            return; // Thoát khỏi hàm nếu khoảng cách thời gian vượt quá 30 ngày
//        }

        double totalIncome = 0;
        double totalExpense = 0;

        messageLabel.setText("");
        String username = PreferencesHelper.getUsername();
        List<TransactionModel> transactions = transactionDao.selectByRangedateAndUsername(username, startDate, endDate);

        // Làm mới danh sách chi tiết
        Incomedetails.clear();
        Expensedetails.clear();

        stat_in_pie_chart.getData().clear();
        stat_out_pie_chart.getData().clear();

        Map<String, Double> incomeData = new HashMap<>();
        Map<String, Double> expenseData = new HashMap<>();

        for (TransactionModel transaction : transactions) {
            if ("INCOME".equalsIgnoreCase(String.valueOf(transaction.getType()))) {
                incomeData.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
            } else if ("EXPENSE".equalsIgnoreCase(String.valueOf(transaction.getType()))) {
                expenseData.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
            }
        }

        totalIncome = addDataToPieChart(stat_in_pie_chart, incomeData, "Income");
        totalExpense = addDataToPieChart(stat_out_pie_chart, expenseData, "Expense");

        stat_income_lbl.setText(String.valueOf(totalIncome) + " $");
        stat_out_lbl.setText(String.valueOf(totalExpense * -1.0) + " $");
        stat_total_lbl.setText(String.valueOf(totalIncome - totalExpense) + " $");
    }


    private double addDataToPieChart(PieChart pieChart, Map<String, Double> data, String transactionType) {
        double threshold = 5.0; // Ngưỡng để xác định các danh mục nhỏ
        double otherTotal = 0.0;
        double totalValue = data.values().stream().mapToDouble(Double::doubleValue).sum(); // Tổng giá trị để tính phần trăm

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            // Tính phần trăm
            double percentage = (entry.getValue() / totalValue) * 100;


            if ("Income".equals(transactionType)) {
                Incomedetails.add(new DetailModel(entry.getKey(), entry.getValue(), String.format("%.1f%%", percentage)));
            } else if ("Expense".equals(transactionType)) {
                Expensedetails.add(new DetailModel(entry.getKey(), entry.getValue(), String.format("%.1f%%", percentage)));
            }


            if (percentage > threshold) {
                String label = entry.getKey() + " (" + String.format("%.1f", percentage) + "%)";
                PieChart.Data chartData = new PieChart.Data(label, entry.getValue());
                pieChart.getData().add(chartData);
            } else {
                otherTotal += entry.getValue();
            }
        }

        if (otherTotal > 0) {
            double otherPercentage = (otherTotal / totalValue) * 100;
            String otherLabel = "Others (" + String.format("%.1f", otherPercentage) + "%)";
            PieChart.Data otherData = new PieChart.Data(otherLabel, otherTotal);
            pieChart.getData().add(otherData);
        }

        return totalValue;
    }


}

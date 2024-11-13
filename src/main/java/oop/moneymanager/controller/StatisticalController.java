package oop.moneymanager.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import oop.moneymanager.dao.TransactionDao;
import oop.moneymanager.model.TransactionModel;
import oop.moneymanager.model.UserModel;

import java.lang.reflect.Array;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class StatisticalController implements Initializable {

    @FXML
    public Label messageLabel;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionDao = new TransactionDao();

        // Đặt giá trị mặc định cho DatePicker
        sta_date_end.setValue(LocalDate.now()); // Ngày kết thúc là ngày hiện tại
        sta_date_start.setValue(LocalDate.now().minusDays(30)); // Ngày bắt đầu là 30 ngày trước ngày hiện tại

        // Cấu hình kích thước cố định cho PieChart
        stat_in_pie_chart.setPrefSize(330, 330);
        stat_in_pie_chart.setMinSize(330, 330);
        stat_in_pie_chart.setMaxSize(330, 330);

        stat_out_pie_chart.setPrefSize(330, 330);
        stat_out_pie_chart.setMinSize(330, 330);
        stat_out_pie_chart.setMaxSize(330, 330);

        // Ẩn nhãn của PieChart
        stat_in_pie_chart.setLabelsVisible(false);
        stat_out_pie_chart.setLabelsVisible(false);

        // Đặt vị trí Legend ở dưới để không làm thu nhỏ biểu đồ
        stat_in_pie_chart.setLegendSide(Side.BOTTOM);
        stat_out_pie_chart.setLegendSide(Side.BOTTOM);

        // Gọi hàm để load dữ liệu khi chọn ngày bắt đầu và kết thúc
        sta_date_start.setOnAction(event -> loadData());
        sta_date_end.setOnAction(event -> loadData());

        // Tải dữ liệu ban đầu với khoảng thời gian mặc định
        loadData();
    }


    private void loadData() {
        LocalDate endDate = sta_date_end.getValue();
        LocalDate startDate = sta_date_start.getValue();

        // Đặt giá trị mặc định nếu người dùng chưa chọn ngày
        if (endDate == null) {
            endDate = LocalDate.now(); // Ngày hiện tại
        }
        if (startDate == null) {
            startDate = endDate.minusDays(30); // 30 ngày trước ngày hiện tại
        }

        // Kiểm tra nếu khoảng thời gian vượt quá 30 ngày
        long daysBetween = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

        if (daysBetween > 30) {
            // Hiển thị thông báo nếu khoảng thời gian vượt quá 30 ngày
            messageLabel.setText("Thời gian vượt quá 30 ngày, vui lòng chọn lại");
            stat_in_pie_chart.getData().clear();
            stat_out_pie_chart.getData().clear();
        } else {
            // Xóa thông báo và tải dữ liệu
            messageLabel.setText("");
            String username = userModel.getUserName();
            ArrayList<TransactionModel> transactions = transactionDao.getTransactionsByUsernameAndDateRange(username, startDate, endDate);

            // Xóa dữ liệu cũ trong PieChart
            stat_in_pie_chart.getData().clear();
            stat_out_pie_chart.getData().clear();

            // Sử dụng Map để gom các danh mục nhỏ thành "Khác" nếu cần thiết
            Map<String, Double> incomeData = new HashMap<>();
            Map<String, Double> outcomeData = new HashMap<>();

            for (TransactionModel transaction : transactions) {
                if (transaction.getIncome() > 0) {
                    incomeData.merge(transaction.getCategory(), transaction.getIncome(), Double::sum);
                }
                if (transaction.getOutcome() > 0) {
                    outcomeData.merge(transaction.getCategory(), transaction.getOutcome(), Double::sum);
                }
            }

            // Giới hạn hiển thị các danh mục lớn và gom các danh mục nhỏ vào "Khác"
            addDataToPieChart(stat_in_pie_chart, incomeData);
            addDataToPieChart(stat_out_pie_chart, outcomeData);
        }
    }

    private void addDataToPieChart(PieChart pieChart, Map<String, Double> data) {
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
        }
    }
}

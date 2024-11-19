package oop.moneymanager.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetailModel {
    private StringProperty category;
    private DoubleProperty amount; // Thêm thuộc tính này
    private StringProperty percentage;

    public DetailModel(String category, Double amount, String percentage) {
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleDoubleProperty(amount); // Khởi tạo thuộc tính
        this.percentage = new SimpleStringProperty(percentage);
    }

    // Getter và phương thức property cho `category`
    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    // Getter và phương thức property cho `amount`
    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    // Getter và phương thức property cho `percentage`
    public String getPercentage() {
        return percentage.get();
    }

    public StringProperty percentageProperty() {
        return percentage;
    }
}

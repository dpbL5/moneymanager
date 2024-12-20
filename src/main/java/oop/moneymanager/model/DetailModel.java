package oop.moneymanager.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetailModel {
    private StringProperty category;
    private DoubleProperty amount;
    private StringProperty percentage;

    public DetailModel(String category, Double amount, String percentage) {
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleDoubleProperty(amount);
        this.percentage = new SimpleStringProperty(percentage);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public String getPercentage() {
        return percentage.get();
    }

    public StringProperty percentageProperty() {
        return percentage;
    }
}

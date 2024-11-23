package oop.moneymanager.model;

import java.time.LocalDate;

public class BudgetModel {
    private int budget_id;
    private String username;
    private LocalDate date_init;
    private double amount;

    public BudgetModel(int budget_id, String username, LocalDate date_init, double amount) {
        this.budget_id = budget_id;
        this.username = username;
        this.date_init = date_init;
        this.amount = amount;
    }
    public BudgetModel( String username,double amount) {
        this.amount = amount;
        this.username = username;
    }
    public BudgetModel() {}

    public int getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(int budget_id) {
        this.budget_id = budget_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate_init() {
        return date_init;
    }

    public void setDate_init(LocalDate date_init) {
        this.date_init = date_init;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

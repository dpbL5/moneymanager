package oop.moneymanager.model;

public class TransactionModel {
    private String transactionID;
    private String category;
    private String income;
    private String outcome;
    private String note;
    private String username;
    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TransactionModel(String transactionID, String category, String income, String outcome, String note, String username, String date) {
        this.transactionID = transactionID;
        this.category = category;
        this.income = income;
        this.outcome = outcome;
        this.note = note;
        this.username = username;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.join(" ",transactionID,category,income,outcome,note,date);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

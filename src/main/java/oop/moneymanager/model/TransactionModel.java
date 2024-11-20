package oop.moneymanager.model;

import java.time.LocalDate;

public class TransactionModel {
    private Integer id;
    private String username;
    private String category;
    private Double amount;
    private String note;
    private LocalDate date;
    private TransactionType type;
    private TransactionKind kind;

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionModel(Integer id, String username, String category, Double amount, String note, LocalDate date,
                            TransactionType type, TransactionKind kind) {
        this.id = id;
        this.username = username;
        this.category = category;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.type = type;
        this.kind = kind;
    }

    public TransactionModel(String category,String type, Double amount) {
        this.type = TransactionType.valueOf(type);
        this.category = category;
        this.amount = amount;
    }

    public String toString() {
        return String.join(" ",
                id.toString(), category,type.toString(),String.valueOf(amount),note,username,kind.toString(),date.toString());
    }


    public enum TransactionType {
        INCOME("INCOME"), EXPENSE("EXPENSE");

        private String value;

        TransactionType(String value) {
            this.value = value;
        }
    }

    public enum TransactionKind {
        CASH("CASH"), BANK_ACCOUNT("BANK_ACCOUNT"), CREDIT_CARD("CREDIT_CARD");

        private String value;

        TransactionKind(String value) {
            this.value = value;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setKind(TransactionKind kind) {
        this.kind = kind;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCategory() {
        return category;
    }

    public Double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionKind getKind() {
        return kind;
    }



}
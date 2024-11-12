package oop.moneymanager.model;

import java.time.LocalDateTime;

public class TransactionModel {

    enum TransactionType {
        INCOME, OUTCOME, TRANSFER
    }

    private String id;
    private Integer accountId;
    private LocalDateTime transactionDateTime;
    private TransactionType type;
    private Long amount;
    private String category;
    private String note;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TransactionModel(String id, Integer accountId, LocalDateTime transactionDateTime, TransactionType type, Long amount, String category, String note) {
        this.id = id;
        this.accountId = accountId;
        this.transactionDateTime = transactionDateTime;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note;
    }
}
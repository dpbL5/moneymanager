package oop.moneymanager.model;

import java.security.PrivateKey;
import java.time.LocalDateTime;

public class TransactionModel {

    public enum TransactionType {
        INCOME("INCOME"),
        EXPENSE("EXPENSE"),
        TRANSFER("TRANSFER");

        private String type;

        TransactionType(String type) {
            this.type = type;
        }
    }

    public enum TransactionSource {
        CASH("CASH"),
        CREDIT_CARD("CREDIT_CARD"),
        BANK_ACCOUNT("BANK_ACCOUNT");


        private String source;
        TransactionSource(String source) {
            this.source = source;
        }
    }

    private String id;
    private String username;
    private LocalDateTime transactionDateTime; // date and time
    private TransactionType type;
    private TransactionSource source;
    private Long amount;
    private String category;
    private String fromAccount;
    private String note;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public TransactionModel(
        String id, String username, LocalDateTime transactionDateTime, TransactionType type,
        Long amount, String category, String note, String fromAccount
        ) {
            this.id = id;
            this.username = username;
            this.transactionDateTime = transactionDateTime;
            this.type = type;
            this.amount = amount;
            this.category = category;
            this.note = note;
            this.fromAccount = fromAccount;
    }

    public TransactionModel() {
        // blank constructor
    }

    @Override
    public String toString() {
        return String.join(" ",
         id, username, transactionDateTime.toString(), type.toString(), amount.toString(), category, fromAccount , note);
    }
}
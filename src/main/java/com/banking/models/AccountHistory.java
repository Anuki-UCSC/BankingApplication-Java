package com.banking.models;

import java.util.ArrayList;
import java.util.Date;

public class AccountHistory {
    private int id;
    private Date transactionDate;
    private String description;
    private float amount;
    private float balance;

    public AccountHistory(int id, Date transactionDate, String description, float amount, float balance) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}

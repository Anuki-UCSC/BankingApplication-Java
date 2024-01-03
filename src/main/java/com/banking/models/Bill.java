package com.banking.models;

public class Bill {
    private String option; // bill option - ex : electricity bill, water bill
    private String toAccountNumber;
    private int amount;

    public Bill() {
    }

    public Bill(String option, String toAccountNumber, int amount) {
        this.option = option;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

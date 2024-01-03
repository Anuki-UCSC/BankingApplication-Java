package com.banking.models;

public class Account {
    private String accountNumber;
    private AccountType accountType;

    public Account() {
    }

    public Account(String accountNumber, AccountType type) {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}

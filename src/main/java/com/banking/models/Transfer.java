package com.banking.models;

public class Transfer {

    private String toAccountNumber;
    private String accountName;
    private String bankName;
    private int branchCode;

    public Transfer() {
    }

    public Transfer(String toAccountNumber, String accountName, String bankName, int branchCode) {
        this.toAccountNumber = toAccountNumber;
        this.accountName = accountName;
        this.bankName = bankName;
        this.branchCode = branchCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
}

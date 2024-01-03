package com.banking.models;

public class BankOption {

    private int number;
    private String displayName;
    private String code;

    public BankOption(int number, String displayName, String code) {
        this.number = number;
        this.displayName = displayName;
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.banking.models;

public class TransferDTO extends Transfer{
    private int amount;
    private String purpose;

    public TransferDTO(String toAccountNumber, String accountName, String bankName, int branchCode, int amount, String purpose) {
        super(toAccountNumber, accountName, bankName, branchCode);
        this.amount = amount;
        this.purpose = purpose;
    }

    public TransferDTO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "amount=" + amount +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}

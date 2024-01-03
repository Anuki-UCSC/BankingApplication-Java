package com.banking.models;


public class BillOwner extends Bill {
    private String customer;
    private String nic;
    private String fromAccountNumber;


    public BillOwner(String option, String toAccountNumber, int amount, String customer, String nic, String fromAccountNumber) {
        super(option, toAccountNumber, amount);
        this.customer = customer;
        this.nic = nic;
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }
}
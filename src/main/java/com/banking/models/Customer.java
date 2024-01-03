package com.banking.models;

public class Customer {
    private int id;
    private String name;
    private String nic;
    private Account account;

    public Customer() {
    }

    public Customer(int id, String name, String nic) {
        this.id = id;
        this.name = name;
        this.nic = nic;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

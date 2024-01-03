package com.banking.services;

import com.banking.models.Customer;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDA;

public class CustomerService {

    private CustomerDA customerDA;

    public CustomerService(CustomerDA customerDA) {
        this.customerDA = customerDA;
    }

    public boolean checkCustomer(Customer customer){
        boolean validated = customerDA.checkCustomer(customer.getName(), customer.getNic(), customer.getAccount().getAccountNumber());
        return validated;
    }

    public void setCustomerData(boolean isValid, Customer customer){
        if(isValid){
            CustomerData.setName(customer.getName());
            CustomerData.setNic(customer.getNic());
            AccountData.setAccountNumber(customer.getAccount().getAccountNumber());
        }
    }
}

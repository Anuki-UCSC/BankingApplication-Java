package com.banking.services;

import com.banking.models.Customer;
import com.banking.models.AccountData;
import com.banking.models.CustomerData;
import com.banking.dataAccess.CustomerDataAccess;

public class CustomerService {

    private CustomerDataAccess customerDataAccess = new CustomerDataAccess();

    public CustomerService() {
    }

    public boolean checkCustomer(Customer customer){
        boolean validated = customerDataAccess.checkCustomer(customer.getName(), customer.getNic(), customer.getAccount().getAccountNumber());
        return validated;
    }

    public void setCustomerData(Customer customer){
            CustomerData.setName(customer.getName());
            CustomerData.setNic(customer.getNic());
            AccountData.setAccountNumber(customer.getAccount().getAccountNumber());
    }
}

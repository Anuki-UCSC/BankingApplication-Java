package com.banking.services;

import com.banking.models.Customer;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDao;

public class CustomerService {

    private CustomerDao customerDao = new CustomerDao();

    public CustomerService() {
    }

    public boolean checkCustomer(Customer customer){
        boolean validated = customerDao.checkCustomer(customer.getName(), customer.getNic(), customer.getAccount().getAccountNumber());
        return validated;
    }

    public void setCustomerData(Customer customer){
            CustomerData.setName(customer.getName());
            CustomerData.setNic(customer.getNic());
            AccountData.setAccountNumber(customer.getAccount().getAccountNumber());
    }
}

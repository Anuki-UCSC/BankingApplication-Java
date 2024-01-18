package com.banking;

import com.banking.shared.exceptions.MinusInputValueException;
import com.banking.views.BankServiceView;
import com.banking.views.CustomerView;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws Exception {

            System.out.println("------ Welcome to Mobile Banking -------");

            //call customer details view
            CustomerView customerView = new CustomerView();
            boolean valid = customerView.askCustomerDetails();

            if (valid) {
                // call bank services view
                BankServiceView bankServiceView = new BankServiceView();
                bankServiceView.provideListOfBankServicesToChoose();
            }

    }
}

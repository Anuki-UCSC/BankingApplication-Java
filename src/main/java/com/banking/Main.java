package com.banking;

import com.banking.shared.exceptions.MinusInputValueException;
import com.banking.views.BankServiceView;
import com.banking.views.CustomerView;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args)  {
        // COMMENT: 1/15/2024 This type of exception handling is ok. Not good. I mean try catching your code in the highest level
        // COMMENT: 1/15/2024  Read about adding how to use try catch block and it's best practices

        // ANSWER : implemented the exception handling in upper levels. but keeping try catch as it is.
        try {
            System.out.println("------ Welcome to Mobile Banking -------");

            //call customer details view
            CustomerView customerView = new CustomerView();
            boolean valid = customerView.askCustomerDetails();

            if (valid) {
                // call bank services view
                BankServiceView bankServiceView = new BankServiceView();
                bankServiceView.provideListOfBankServicesToChoose();
            }
        } catch (Exception e){
            System.out.println("Error Occurred : " + e);
        }
    }
}

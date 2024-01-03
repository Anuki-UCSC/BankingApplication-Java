package com.banking.views;

import com.banking.models.Account;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.shared.validations.InputValidations;
import com.banking.dataAccess.CustomerDA;
import com.banking.models.Customer;
import com.banking.services.CustomerService;

import java.util.Scanner;

public class CustomerView {

    private Scanner scanner = new Scanner(System.in);
    private InputValidations inputValidations = new InputValidations();
    private Customer user = new Customer();
    private CustomerDA customerDA = new CustomerDA();

    public boolean askCustomerDetails(){
        CustomerService customerService = new CustomerService(customerDA);

        this.getNameAsUserInput();

        this.getNicAsUserInput();

        this.getAccountNumberAsUserInput();

        boolean isValid = customerService.checkCustomer(user);

        customerService.setCustomerData(isValid, user);

        return isValid;
    }

    private void getAccountNumberAsUserInput() {
        String accountNumber;
        boolean isValidAccountNumber;
        do{
            System.out.print("Enter your Account Number : ");
            accountNumber = scanner.next();
            isValidAccountNumber = inputValidations.validateAccountNumber(accountNumber);
            if(isValidAccountNumber){
                Account account = new Account();
                account.setAccountNumber(accountNumber);
                user.setAccount(account);
                AccountData.setAccountNumber(user.getAccount().getAccountNumber());
                System.out.println(user.getAccount().getAccountNumber());
            }
        }while (!isValidAccountNumber);
    }

    private void getNicAsUserInput() {
        String nic;
        boolean isValidNic;
        do{
            System.out.print("Enter your NIC : ");
            nic = scanner.next();
            isValidNic = inputValidations.validateNIC(nic);
            if(isValidNic){
                user.setNic(nic);
                System.out.println(user.getNic());
            }
        }while (!isValidNic);
    }

    private void getNameAsUserInput(){
        String name;
        boolean isValidName;
        do{
            System.out.print("Enter name with initials : ");
            name = scanner.nextLine();
            isValidName = inputValidations.validateName(name);
            if(isValidName){
                user.setName(name);
                System.out.println(user.getName());
            }
        }while (!isValidName);
    }
}

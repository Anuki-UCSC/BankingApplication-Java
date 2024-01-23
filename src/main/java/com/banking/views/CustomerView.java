package com.banking.views;

import com.banking.models.Account;
import com.banking.models.AccountData;
import com.banking.validations.InputValidations;
import com.banking.models.Customer;
import com.banking.services.CustomerService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.banking.utility.ExceptionHandler.handleException;

public class CustomerView {
    // COMMENT: 1/15/2024 Is there a any particular reason for defining these variables globally?
    // COMMENT: 1/15/2024 If you change the value of one of below objects in one place, it will affected globally since these objects are define globally.
    // ANSWER : yes. scanner , inputValidations do not have to declare in every method separately. So, that it will be used as shared global variables
    // user object is needed to have as a global variable since some methods in this scope need one user object that is shared, So that those methods ether responsible in setting attributes of the object / getting values of that shared object.

    private Scanner scanner = new Scanner(System.in);
    private InputValidations inputValidations = new InputValidations();
    private Customer user = new Customer();


    public boolean askCustomerDetails() throws Exception {
        boolean isValid = false;
        try {
            CustomerService customerService = new CustomerService();

            // COMMENT: 1/15/2024 This method naming convention is wrong. If the method is get, there should be a return type. But here it is void.
            // ANSWER : name of method changed according to the correct duty -> ask for user input(Name) until it is valid, and remove setting user object from the functions
            String name = this.askUserInputUntilValidName();

            String nic = this.askUserInputUntilValidNic();

            String accountNumber = this.askUserInputUntilValidAccount();

            this.setUser(name, nic, accountNumber);

            isValid = customerService.checkCustomer(user);

            if(isValid) {
                // COMMENT: 1/15/2024 Change the execution of these methods.  getNameAsUserInput(), getNicAsUserInput(), getAccountNumberAsUserInput()
                // COMMENT: 1/15/2024 As name implies, get required parameter from each method. then if valid, set them into the model
                // Answer : Done
                customerService.setCustomerData(user);
                AccountData.setAccountNumber(user.getAccount().getAccountNumber());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            handleException("OutOfBound Error Occurred", e);
        } catch (ArithmeticException e) {
            handleException("Arithmetic Error Occurred", e);
        } catch (InputMismatchException e) {
            handleException("Invalid Input Error Occurred", e);
        } catch (Exception e) {
            handleException("Error Occurred", e);
        }

        return isValid;
    }

    private void setUser(String name, String nic, String accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        user.setName(name);
        user.setNic(nic);
        user.setAccount(account);
    }

    private String askUserInputUntilValidAccount() {
        String accountNumber;
        boolean isValidAccountNumber;
        do{
            System.out.print("Enter your Account Number : ");
            accountNumber = scanner.next();
            isValidAccountNumber = inputValidations.validateAccountNumber(accountNumber);
            if(isValidAccountNumber){
                System.out.println(accountNumber);
            }else {
                System.out.println("Invalid input! Please enter valid Account Number.");
            }
        }while (!isValidAccountNumber);
        return accountNumber;
    }

    private String askUserInputUntilValidNic() {
        String nic;
        boolean isValidNic;
        do{
            System.out.print("Enter your NIC : ");
            nic = scanner.next();
            isValidNic = inputValidations.validateNIC(nic);
            if(isValidNic){
                System.out.println(nic);
            }else {
                System.out.println("Invalid input! Please enter valid NIC.");
            }
        }while (!isValidNic);
        return nic;
    }

    private String askUserInputUntilValidName(){
        String name;
        boolean isValidName;
        do{
            System.out.print("Enter name with initials : ");
            name = scanner.nextLine();
            isValidName = inputValidations.validateName(name);
            if(isValidName){
                System.out.println(name);
            }else{
                System.out.println("Invalid input! Please enter valid name.");
            }
        }while (!isValidName);
        return name;
    }
}

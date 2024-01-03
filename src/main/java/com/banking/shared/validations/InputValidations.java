package com.banking.shared.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {

    public boolean validateName(String name){
        boolean isValid = false;
        Pattern pattern = Pattern.compile("[!@#$%^&*()_+{}|]");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Invalid input! Please enter valid name.");
        } else {
            isValid = true;
        }
        return isValid;
    }

    public boolean validateNIC(String nic){
        boolean isValid = false;
        Pattern pattern = Pattern.compile("^(?i)([0-9]{9}[xXvV]|[0-9]{12})$");
        Matcher matcher = pattern.matcher(nic);
        boolean matchFound = matcher.find();
        if(matchFound) {
            isValid = true;
        } else {
            System.out.println("Invalid input! Please enter valid NIC.");
        }
        return isValid;
    }

    public boolean validateAccountNumber(String accountNumber){
        boolean isValid = false;
        Pattern pattern = Pattern.compile("^\\d{9,}$");
        Matcher matcher = pattern.matcher(accountNumber);
        boolean matchFound = matcher.find();
        if(matchFound) {
            isValid = true;
        } else {
            System.out.println("Invalid input! Please enter valid Account Number.");
        }
        return isValid;
    }
}

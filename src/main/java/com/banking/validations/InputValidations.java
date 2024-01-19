package com.banking.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {

    public boolean validateName(String name){
        Pattern pattern = Pattern.compile("[!@#$%^&*()_+{}|]");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return false; // validation fails because of existence of spacial characters
        }
        return true;
    }

    public boolean validateNIC(String nic){
        Pattern pattern = Pattern.compile("^(?i)([0-9]{9}[xXvV]|[0-9]{12})$");
        Matcher matcher = pattern.matcher(nic);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            return false; // validation fails because, pattern of Nic NOT found
        }
        return true;
    }

    public boolean validateAccountNumber(String accountNumber){
        Pattern pattern = Pattern.compile("^\\d{9,}$");
        Matcher matcher = pattern.matcher(accountNumber);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            return false; // validation fails because, pattern of account number NOT found
        }
        return true;
    }
}

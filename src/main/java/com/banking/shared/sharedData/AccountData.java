package com.banking.shared.sharedData;

public class AccountData {

    private static String accountNumber;

    public static String getAccountNumber() {
        return accountNumber;
    }

    public static void setAccountNumber(String accountNumber) {
        AccountData.accountNumber = accountNumber;
    }
}

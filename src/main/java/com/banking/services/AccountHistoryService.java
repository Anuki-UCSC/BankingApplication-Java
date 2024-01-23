package com.banking.services;

import com.banking.dataAccess.AccountHistoryDataAccess;
import com.banking.models.AccountHistory;

import java.util.ArrayList;

public class AccountHistoryService {

    private AccountHistoryDataAccess accountHistoryDataAccess = new AccountHistoryDataAccess();

    public AccountHistoryService() {
    }

    public ArrayList<AccountHistory> getAccountHistory(String accountId){
        return accountHistoryDataAccess.getAccountHistory(accountId);
    }

}

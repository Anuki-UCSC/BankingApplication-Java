package com.banking.services;

import com.banking.dataAccess.AccountHistoryDA;
import com.banking.models.AccountHistory;

import java.util.ArrayList;

public class AccountHistoryService {

    private AccountHistoryDA accountHistoryDA;

    public AccountHistoryService(AccountHistoryDA accountHistoryDA) {
        this.accountHistoryDA = accountHistoryDA;
    }

    public ArrayList<AccountHistory> getAccountHistory(String accountId){
        return accountHistoryDA.getAccountHistory(accountId);
    }

}

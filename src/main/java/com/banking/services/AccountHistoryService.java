package com.banking.services;

import com.banking.dataAccess.AccountHistoryDao;
import com.banking.models.AccountHistory;

import java.util.ArrayList;

public class AccountHistoryService {

    private AccountHistoryDao accountHistoryDao = new AccountHistoryDao();

    public AccountHistoryService() {
    }

    public ArrayList<AccountHistory> getAccountHistory(String accountId){
        return accountHistoryDao.getAccountHistory(accountId);
    }

}

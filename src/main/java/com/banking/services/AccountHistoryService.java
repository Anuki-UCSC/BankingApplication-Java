package com.banking.services;

import com.banking.dataAccess.AccountHistoryDao;
import com.banking.models.AccountHistory;

import java.util.ArrayList;

public class AccountHistoryService {

    private AccountHistoryDao accountHistoryDao;

    public AccountHistoryService(AccountHistoryDao accountHistoryDao) {
        this.accountHistoryDao = accountHistoryDao;
    }

    public ArrayList<AccountHistory> getAccountHistory(String accountId){
        return accountHistoryDao.getAccountHistory(accountId);
    }

}

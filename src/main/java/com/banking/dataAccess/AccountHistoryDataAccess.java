package com.banking.dataAccess;

import com.banking.models.AccountHistory;

import java.util.ArrayList;
import java.util.Date;

public class AccountHistoryDataAccess {
    public ArrayList<AccountHistory> getAccountHistory(String accountId){
        ArrayList<AccountHistory> accountHistorylist = new ArrayList<>();
        accountHistorylist.add(new AccountHistory(1, new Date(), "Deposit", 100.0f, 100.0f));
        accountHistorylist.add(new AccountHistory(2, new Date(), "Withdrawal", 50.0f, 50.0f));
        accountHistorylist.add(new AccountHistory(3, new Date(), "Purchase", 30.0f, 20.0f));
        accountHistorylist.add(new AccountHistory(4, new Date(), "Deposit", 200.0f, 220.0f));

        return accountHistorylist;
    }
}

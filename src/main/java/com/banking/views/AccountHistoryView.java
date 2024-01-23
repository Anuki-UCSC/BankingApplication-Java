package com.banking.views;

import com.banking.models.AccountHistory;
import com.banking.services.AccountHistoryService;
import com.banking.models.AccountData;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AccountHistoryView implements BankingView{


    public AccountHistoryView() {
    }

    public void showView() throws Exception {
        System.out.println("######################################################");
        System.out.println();
        System.out.println();
        System.out.println("################# 1. ACCOUNT HISTORY ##################");
        AccountHistoryService accountHistoryService = new AccountHistoryService();
        ArrayList<AccountHistory> accountHistory = accountHistoryService.getAccountHistory(AccountData.getAccountNumber());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (AccountHistory record: accountHistory) {
            System.out.printf(" %d \t %s \t %15s \t %8.2f \t %10.2f%n",
                    record.getId(), record.getTransactionDate().toString(), record.getDescription(), record.getAmount(), record.getBalance());
        }

        BackToMenuView backToMenuView = new BackToMenuView();
        backToMenuView.showView();

    }
}

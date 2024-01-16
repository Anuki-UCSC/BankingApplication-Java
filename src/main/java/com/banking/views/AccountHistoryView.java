package com.banking.views;

import com.banking.dataAccess.AccountHistoryDao;
import com.banking.models.AccountHistory;
import com.banking.services.AccountHistoryService;
import com.banking.shared.sharedData.AccountData;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AccountHistoryView implements BankingView{

    private AccountHistoryService accountHistoryService;
    private BackToMenuView backToMenuView;
    private AccountHistoryDao accountHistoryDao = new AccountHistoryDao();

    public AccountHistoryView() {
    }

    public void showView() throws Exception {
        System.out.println("######################################################");
        System.out.println();
        System.out.println();
        System.out.println("################# 1. ACCOUNT HISTORY ##################");
        this.accountHistoryService = new AccountHistoryService(accountHistoryDao);
        ArrayList<AccountHistory> accountHistory = this.accountHistoryService.getAccountHistory(AccountData.getAccountNumber());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (AccountHistory record: accountHistory) {
            System.out.printf(" %d \t %s \t %15s \t %8.2f \t %10.2f%n",
                    record.getId(), record.getTransactionDate().toString(), record.getDescription(), record.getAmount(), record.getBalance());
        }

        this.backToMenuView = new BackToMenuView();
        this.backToMenuView.showView();

    }
}

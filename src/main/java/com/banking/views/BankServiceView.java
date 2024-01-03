package com.banking.views;

import java.util.Scanner;

public class BankServiceView {

    public void provideListOfBankServicesToChoose() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("######################################################");
        System.out.println("################ BANKING SERVICES ####################");
        System.out.println("  1. ACCOUNT HISTORY ");
        System.out.println("  2. BILL PAYMENT");
        System.out.println("  3. TRANSFER MONEY ");
        System.out.println("  4. CONTACT BANK ");
        System.out.println(" ");
        System.out.println("######################################################");
        System.out.print("Select the option number : ");
        int serviceNumber = scanner.nextInt();

        switch (serviceNumber) {
            case 1:
                AccountHistoryView accountHistoryView = new AccountHistoryView();
                accountHistoryView.showAccountHistory();
                break;
            case 2:
                BillPaymentView billPaymentView = new BillPaymentView();
                billPaymentView.showBillPayment();
                break;
            case 3:
                TransferMoneyView transferMoneyView = new TransferMoneyView();
                transferMoneyView.showTransferMoney();
                break;
            case 4:
                ContactBankView contactBankView = new ContactBankView();
                contactBankView.showContactBank();
                break;
            default:
                System.out.println("-----------------");
                System.out.printf("Unexpected input %d !",serviceNumber);
                BackToMenuView backToMenuView = new BackToMenuView();
                boolean backToMenu = backToMenuView.BackToMenu();
        }
    }
}

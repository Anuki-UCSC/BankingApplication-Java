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

        BankingView view = null;

        switch (serviceNumber) {
            case 1:
                view = new AccountHistoryView();
                break;
            case 2:
                view = new BillPaymentView();
                break;
            case 3:
                view = new TransferMoneyView();
                break;
            case 4:
                view = new ContactBankView();
                break;
            default:
                System.out.println("-----------------");
                System.out.printf("Unexpected input %d !",serviceNumber);
                view = new BackToMenuView();
                break;
        }
        view.showView();

    }
}

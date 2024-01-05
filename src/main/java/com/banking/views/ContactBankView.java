package com.banking.views;

import com.banking.views.BackToMenuView;

public class ContactBankView implements BankingView {

    public void showView() throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("################# 4. CONTACT BANK  ##################");
        System.out.println();
        System.out.println("This feature is currently NOT available!");
        System.out.println();
        BackToMenuView backToMenuView = new BackToMenuView();
        backToMenuView.showView();
    }

}

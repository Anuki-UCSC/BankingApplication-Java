package com.banking.shared.utility;

import com.banking.views.BackToMenuView;

public class ExceptionHandler {
    public static void handleException(String message, Exception e) throws Exception {
        System.out.println(message + " : " + e);
        BackToMenuView backToMenuView = new BackToMenuView();
        backToMenuView.showView();
    }
}

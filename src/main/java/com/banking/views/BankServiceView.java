package com.banking.views;

import com.banking.shared.exceptions.MinusInputValueException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankServiceView {

    public void provideListOfBankServicesToChoose() throws Exception {
        try {
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
                    System.out.println("hello");
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


        } catch (ArrayIndexOutOfBoundsException e) {
            handleException("OutOfBound Error Occurred", e);
        } catch (ArithmeticException e) {
            handleException("Arithmetic Error Occurred", e);
        } catch (InputMismatchException e) {
            handleException("Invalid Input Error Occurred", e);
        } catch (MinusInputValueException e) {
            handleException("Invalid Input With Minus Values", e);
        } catch (Exception e) {
            handleException("Error Occurred", e);
        }
    }

    private static void handleException(String message, Exception e) throws Exception {
        System.out.println(message + " : " + e);
        BackToMenuView backToMenuView = new BackToMenuView();
        backToMenuView.showView();
    }

}

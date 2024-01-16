package com.banking.views;

import com.banking.shared.exceptions.MinusInputValueException;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.banking.shared.utility.ExceptionHandler.handleException;

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
                        // COMMENT: 1/15/2024 Assume that  ArrayIndexOutOfBoundsException has occurred. Does the execution comes to this line or goes to line 58. Check that.
                        // ANSWER: Checked. Hitting array out of bound scenarios here. detected another scenario and corrected. working fine now.
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
}

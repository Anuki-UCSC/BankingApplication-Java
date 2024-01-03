package com.banking.views;

import com.banking.dataAccess.AccountDA;
import com.banking.dataAccess.BillDA;
import com.banking.models.Bill;
import com.banking.services.BillService;
import com.banking.services.OtpService;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillPaymentView {

    private String selectedOption;
    private String accountNumber;
    private int amount;

    private BackToMenuView backToMenuView;
    private BillService billService;
    private AccountDA accountDA = new AccountDA();
    private CustomerDA customerDA = new CustomerDA();
    private OtpService otpService = new OtpService();
    private BillDA billDA = new BillDA();

    private ArrayList<String> billOptions = new ArrayList<String>(
            List.of("electricity bill",
                    "water bill",
                    "mobitel bill",
                    "dialog bill"));

    public Scanner scanner = new Scanner(System.in);
    public void showBillPayment() throws Exception {

        System.out.println();
        System.out.println();
        System.out.println("################# 3. BILL PAYMENT  ##################");
        System.out.println();
        int count = 0;
        for(String option : billOptions){
            System.out.printf("%d . %s", ++count,option);
            System.out.println();
        }
        System.out.print("Enter option number : ");
        int selectedOptionNumber = scanner.nextInt();
        selectedOption = billOptions.get(selectedOptionNumber-1);

        //balance validation
        boolean isvalid = this.accountNumberAndAmountValid(selectedOption);

        //If invalid route to menu
        this.invalidWarningAndRouteToMenu(isvalid);

        // otp
        int enteredOtp = this.handleOTP();

        //transaction
        this.handleTransaction(enteredOtp);
    }

    public boolean accountNumberAndAmountValid(String selectedOption){
        System.out.print("Enter Account Number :");
        accountNumber = scanner.next();
        System.out.println();
        System.out.print("Enter Amount :");
        amount  = scanner.nextInt();

        billService = new BillService(accountDA, otpService, customerDA, billDA);
        Bill bill = new Bill(selectedOption,accountNumber,amount);
        return billService.accountBalanceValidation(bill);
    }

    public int handleOTP(){
        System.out.println("Sending OTP....");
        System.out.println();
        billService = new BillService(accountDA, otpService, customerDA, billDA);
        billService.sendOTP(CustomerData.getName(), CustomerData.getNic());

        System.out.println("OTP has been sent to your registered phone number!");
        System.out.print("Enter OTP : ");
        int enteredOtp = scanner.nextInt();
        return  enteredOtp;
    }

    public void handleTransaction(int otp) throws Exception {
        Bill bill = new Bill(selectedOption, accountNumber, amount);
        boolean transactionSuccess = billService.authorizeAndProceedTransaction(otp, bill );
        if(transactionSuccess){
            System.out.println("Transaction success!");
            System.out.printf("LKR %d has been successfully paid to account %s.",amount, accountNumber);
            System.out.println();
            this.backToMenuView = new BackToMenuView();
            boolean backToMenu = this.backToMenuView.BackToMenu();
        }else{
            System.out.println("Transaction failed!");
            this.backToMenuView = new BackToMenuView();
            boolean backToMenu = this.backToMenuView.BackToMenu();
        }
    }

    public void invalidWarningAndRouteToMenu(boolean isvalid) throws Exception {
        if(!isvalid) {
            System.out.println("Your account balance in insufficient! Unable to proceed.");
            this.backToMenuView = new BackToMenuView();
            boolean backToMenu = this.backToMenuView.BackToMenu();
        }
    }

}

package com.banking.views;

import com.banking.dataAccess.AccountDao;
import com.banking.dataAccess.BillDao;
import com.banking.models.Bill;
import com.banking.services.BillService;
import com.banking.services.OtpService;
import com.banking.shared.exceptions.MinusInputValueException;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillPaymentView implements BankingView {

    private String selectedOption;
    private String accountNumber;
    private int amount;

    private BackToMenuView backToMenuView;
    private BillService billService;
    private ArrayList<String> billOptions = new ArrayList<String>(
            List.of("electricity bill",
                    "water bill",
                    "mobitel bill",
                    "dialog bill"));

    public Scanner scanner = new Scanner(System.in);
    public void showView() throws Exception {

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
        int selectedOptionNumber = 0;
        selectedOptionNumber = scanner.nextInt();
        if(selectedOptionNumber < 0){
            throw new MinusInputValueException("options does not accept minus values.");
        }

        try {
            selectedOption = billOptions.get(selectedOptionNumber-1);
        }catch (IndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Invalid bill option: "+ selectedOptionNumber+ " out of bounds for length "+ billOptions.size());
        }


        //balance validation
        boolean isvalid = this.accountNumberAndAmountValid(selectedOption);

        //If invalid route to menu
        this.invalidWarningAndRouteToMenu(isvalid);

        // otp
        int enteredOtp = this.handleOTP();

        //transaction
        this.handleTransaction(enteredOtp);
    }

    public boolean accountNumberAndAmountValid(String selectedOption) throws MinusInputValueException {
        System.out.print("Enter Account Number :");
        accountNumber = scanner.next();
        System.out.println();
        System.out.print("Enter Amount :");
        amount  = scanner.nextInt();
        if (amount<0){
            throw new MinusInputValueException("Amount cannot be a minus value");
        }

        billService = new BillService();
        Bill bill = new Bill(selectedOption,accountNumber,amount);
        return billService.accountBalanceValidation(bill);
    }

    public int handleOTP(){
        System.out.println("Sending OTP....");
        System.out.println();
        billService = new BillService();
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
            this.backToMenuView.showView();
        }else{
            System.out.println("Transaction failed!");
            this.backToMenuView = new BackToMenuView();
            this.backToMenuView.showView();
        }
    }

    public void invalidWarningAndRouteToMenu(boolean isvalid) throws Exception {
        if(!isvalid) {
            System.out.println("Your account balance in insufficient! Unable to proceed.");
            this.backToMenuView = new BackToMenuView();
            this.backToMenuView.showView();
        }
    }

}

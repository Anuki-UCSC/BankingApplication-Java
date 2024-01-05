package com.banking.views;

import com.banking.dataAccess.AccountDA;
import com.banking.services.OtpService;
import com.banking.shared.sharedData.CustomerData;
import com.banking.shared.validations.InputValidations;
import com.banking.dataAccess.CustomerDA;
import com.banking.dataAccess.TransferDA;
import com.banking.models.BankOption;
import com.banking.models.Transfer;
import com.banking.models.TransferDTO;
import com.banking.services.TransferService;

import java.util.ArrayList;
import java.util.Scanner;

public class TransferMoneyView implements BankingView{


    private BackToMenuView backToMenuView;
    private TransferService transferService;
    private TransferDA transferDA;
    private AccountDA accountDA;
    private CustomerDA customerDA;
    private TransferDTO transferDTO;
    private OtpService otpService;
    private ArrayList<BankOption> bankOptionsList = new ArrayList<BankOption>();
    public Scanner scanner = new Scanner(System.in);
    private final InputValidations inputValidations = new InputValidations();


    public void showView() {
        BankOption bankOption1 = new BankOption(1, "Own Bank", "OWN");
        BankOption bankOption2 = new BankOption(2, "Other Bank", "OTHER");
        bankOptionsList.add(bankOption1);
        bankOptionsList.add(bankOption2);
        this.transferDTO = new TransferDTO();
        this.transferDA = new TransferDA();
        this.accountDA = new AccountDA();
        this.customerDA = new CustomerDA();
        this.otpService = new OtpService();
        this.transferService = new TransferService(transferDA, accountDA, customerDA, otpService);
    }

    public void showTransferMoney() throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("################# 2. TRANSFER MONEY  ##################");
        System.out.println();

        //show Transfer Service options
        this.displayServiceOptions();

        //get input option
        System.out.print("Enter option : ");
        int bankOption = scanner.nextInt();

        //get input account number
        String toAccountNumber = this.getTransferAccountNumberAsUserInput();

        //redirect To Service Options
        this.redirectToServiceOptionsOrMenu(bankOption,toAccountNumber);

        //account Balance Validation
        boolean isValid = transferService.accountBalanceValidation(transferDTO);

        //If invalid, Route Back To Menu
        this.invalidWarningAndRouteToMenu(isValid);

        //handle otp
        int enteredOtp = this.handleOTP();

        //handle Transaction
        this.handleTransaction(enteredOtp);

    }

    public void displayServiceOptions(){
        for (BankOption option: bankOptionsList) {
            System.out.printf("%d . %s \n", option.getNumber(), option.getDisplayName());
        }
    }
    private String getTransferAccountNumberAsUserInput(){
        String toAccountNumber;
        boolean isValidAccountNumber;
        do{
            System.out.print("Enter Account Number : ");
            toAccountNumber = scanner.next();
            isValidAccountNumber = inputValidations.validateAccountNumber(toAccountNumber);
            if(isValidAccountNumber){
                transferDTO.setToAccountNumber(toAccountNumber);
            }
        }while (!isValidAccountNumber);
        return toAccountNumber;
    }

    private void redirectToServiceOptionsOrMenu(int bankOption, String toAccountNumber ) throws Exception {
        if(bankOption == 1 || bankOption == 2){
            String code = bankOptionsList.stream()
                    .filter(option -> option.getNumber() == bankOption)
                    .map(BankOption::getCode)
                    .findFirst().orElse(null);

            this.receiverDetails(code, toAccountNumber);
        }else {
            System.out.println("Invalid input");
            this.backToMenuView = new BackToMenuView();
            this.backToMenuView.showView();
        }
    }
    public void receiverDetails(String code, String accountNumber) throws Exception {
        switch (code){
            case "OWN":
                this.ownAccount(accountNumber);
                break;

            case "OTHER":
                this.otherAccount(accountNumber);
                break;

            default:
                this.backToMenuView = new BackToMenuView();
                this.backToMenuView.showView();
                break;
        }
    }

    private void ownAccount(String accountNumber){
        Transfer receiverDetails = transferService.getReceiverDetails(accountNumber);
        transferDTO.setAccountName(receiverDetails.getAccountName());
        transferDTO.setBankName(receiverDetails.getBankName());
        transferDTO.setBranchCode(receiverDetails.getBranchCode());
        System.out.println("Account owner : " + transferDTO.getAccountName());
        System.out.println("Bank Name     : " + transferDTO.getBankName());
        System.out.println("Branch Code   : " + transferDTO.getBranchCode());
        System.out.print("Amount        : ");
        transferDTO.setAmount(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Purpose       : ");
        transferDTO.setPurpose(scanner.nextLine());
    }

    private void otherAccount(String accountNumber) {
        scanner.nextLine();
        System.out.print("Account Name : ");
        transferDTO.setAccountName(scanner.nextLine());
        System.out.print("Bank Name     : ");
        transferDTO.setBankName(scanner.nextLine());
        System.out.print("Branch Code   : ");
        transferDTO.setBranchCode(scanner.nextInt());
        System.out.print("Amount        : ");
        transferDTO.setAmount(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Purpose       : ");
        transferDTO.setPurpose(scanner.nextLine());
    }

    public int handleOTP(){
        System.out.println("Sending OTP....");
        System.out.println();
        transferService.sendOTP(CustomerData.getName(), CustomerData.getNic());

        System.out.println("OTP has been sent to your registered phone number!");
        System.out.print("Enter OTP : ");
        int enteredOtp = scanner.nextInt();
        return  enteredOtp;
    }

    public void handleTransaction(int otp) throws Exception {

        boolean transactionSuccess = transferService.authorizeAndProceedTransaction(otp, transferDTO );
        if(transactionSuccess){
            System.out.println("Transaction success!");
            System.out.printf("LKR %d has been successfully paid to account %s.",transferDTO.getAmount(), transferDTO.getToAccountNumber());
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

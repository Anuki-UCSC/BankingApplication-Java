package com.banking.services;

import com.banking.dataAccess.AccountDataAccess;
import com.banking.models.AccountData;
import com.banking.models.CustomerData;
import com.banking.dataAccess.CustomerDataAccess;
import com.banking.dataAccess.TransferDataAccess;
import com.banking.models.Transfer;
import com.banking.models.TransferDTO;

public class TransferService {
    private TransferDataAccess transferDataAccess = new TransferDataAccess();
    private AccountDataAccess accountDataAccess = new AccountDataAccess();
    private CustomerDataAccess customerDataAccess = new CustomerDataAccess();
    private OtpService otpService = new OtpService();

    public TransferService() {
    }

    public Transfer getReceiverDetails(String accountNumber) {
        Transfer receiverDetails = transferDataAccess.getReceiverDetailsByAccountNumber();
        return receiverDetails;
    }

    public boolean accountBalanceValidation(TransferDTO transferDTO) {
        boolean isValid = accountDataAccess.accountBalanceValidation(
                AccountData.getAccountNumber(),
                CustomerData.getNic(),
                transferDTO.getAmount()
        );
        return isValid;
    }

    public void sendOTP(String user, String nic){
        String phoneNumber = customerDataAccess.retrievePhoneNumber(user, nic);
        boolean success = otpService.sentOTP(phoneNumber);
    }

    public boolean authorizeAndProceedTransaction(int userGivenOtp, TransferDTO transferDto){
        boolean authorize = otpService.authorizeOTP(userGivenOtp);
        if(!authorize){
            return false;
        }
        String customer = CustomerData.getName();
        String nic = CustomerData.getNic();
        String fromAccountNumber = AccountData.getAccountNumber();
        boolean success = transferDataAccess.transaction(transferDto);
        if (!success) {
            return false;
        }
        return true;
    }
}

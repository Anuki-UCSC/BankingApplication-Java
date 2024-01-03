package com.banking.services;

import com.banking.dataAccess.AccountDA;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDA;
import com.banking.dataAccess.TransferDA;
import com.banking.models.Transfer;
import com.banking.models.TransferDTO;

public class TransferService {
    private TransferDA transferDA;
    private AccountDA accountDA;
    private CustomerDA customerDA;
    private OtpService otpService;

    public TransferService(TransferDA transferDA, AccountDA accountDA, CustomerDA customerDA, OtpService otpService) {
        this.transferDA = transferDA;
        this.accountDA = accountDA;
        this.customerDA = customerDA;
        this.otpService = otpService;
    }

    public Transfer getReceiverDetails(String accountNumber) {
        Transfer receiverDetails = transferDA.getReceiverDetailsByAccountNumber();
        return receiverDetails;
    }

    public boolean accountBalanceValidation(TransferDTO transferDTO) {
        boolean isValid = accountDA.accountBalanceValidation(
                AccountData.getAccountNumber(),
                CustomerData.getNic(),
                transferDTO.getAmount()
        );
        return isValid;
    }

    public void sendOTP(String user, String nic){
        String phoneNumber = customerDA.retrievePhoneNumber(user, nic);
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
        boolean success = transferDA.transaction(transferDto);
        if (!success) {
            return false;
        }
        return true;
    }
}

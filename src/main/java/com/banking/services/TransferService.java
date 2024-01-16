package com.banking.services;

import com.banking.dataAccess.AccountDao;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDao;
import com.banking.dataAccess.TransferDao;
import com.banking.models.Transfer;
import com.banking.models.TransferDTO;

public class TransferService {
    private TransferDao transferDao;
    private AccountDao accountDao;
    private CustomerDao customerDao;
    private OtpService otpService;

    public TransferService(TransferDao transferDao, AccountDao accountDao, CustomerDao customerDao, OtpService otpService) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.customerDao = customerDao;
        this.otpService = otpService;
    }

    public Transfer getReceiverDetails(String accountNumber) {
        Transfer receiverDetails = transferDao.getReceiverDetailsByAccountNumber();
        return receiverDetails;
    }

    public boolean accountBalanceValidation(TransferDTO transferDTO) {
        boolean isValid = accountDao.accountBalanceValidation(
                AccountData.getAccountNumber(),
                CustomerData.getNic(),
                transferDTO.getAmount()
        );
        return isValid;
    }

    public void sendOTP(String user, String nic){
        String phoneNumber = customerDao.retrievePhoneNumber(user, nic);
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
        boolean success = transferDao.transaction(transferDto);
        if (!success) {
            return false;
        }
        return true;
    }
}

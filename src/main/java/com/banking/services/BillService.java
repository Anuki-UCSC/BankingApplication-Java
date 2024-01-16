package com.banking.services;

import com.banking.dataAccess.AccountDao;
import com.banking.dataAccess.BillDao;
import com.banking.models.Bill;
import com.banking.models.BillOwner;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDao;

public class BillService {
    private AccountDao accountDao;
    private OtpService otpService;
    private CustomerDao customerDao;
    private BillDao billDao;

    public BillService(AccountDao accountDao, OtpService otpService, CustomerDao customerDao, BillDao billDao) {
        this.accountDao = accountDao;
        this.otpService = otpService;
        this.customerDao = customerDao;
        this.billDao = billDao;
    }

    public boolean accountBalanceValidation(Bill bill){
        String customer = CustomerData.getName();
        String nic = CustomerData.getNic();
        String fromAccountNumber = AccountData.getAccountNumber();

        boolean isValid = this.accountDao.accountBalanceValidation(fromAccountNumber, nic, bill.getAmount());

        return isValid;
    }

    public void sendOTP(String user, String nic){
        String phoneNumber = customerDao.retrievePhoneNumber(user, nic);
        boolean success = otpService.sentOTP(phoneNumber);
    }

    public boolean authorizeAndProceedTransaction(int userGivenOtp, Bill bill){
        boolean authorize = otpService.authorizeOTP(userGivenOtp);
        if(!authorize){
            return false;
        }

        BillOwner billOwner = new BillOwner(
                bill.getOption(),
                bill.getToAccountNumber(),
                bill.getAmount(),
                CustomerData.getName(),
                CustomerData.getNic(),
                AccountData.getAccountNumber()
                );
        boolean success = billDao.billTransaction(billOwner);
        if (!success) {
            return false;
        }
        return true;
    }
}

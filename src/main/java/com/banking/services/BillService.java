package com.banking.services;

import com.banking.dataAccess.AccountDao;
import com.banking.dataAccess.BillDao;
import com.banking.models.Bill;
import com.banking.models.BillOwner;
import com.banking.models.AccountData;
import com.banking.models.CustomerData;
import com.banking.dataAccess.CustomerDao;

public class BillService {
    private BillDao billDao = new BillDao();
    private AccountDao accountDao = new AccountDao();
    private CustomerDao customerDao = new CustomerDao();
    private OtpService otpService = new OtpService();

    public BillService() {
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

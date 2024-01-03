package com.banking.services;

import com.banking.dataAccess.AccountDA;
import com.banking.dataAccess.BillDA;
import com.banking.models.Bill;
import com.banking.models.BillOwner;
import com.banking.shared.sharedData.AccountData;
import com.banking.shared.sharedData.CustomerData;
import com.banking.dataAccess.CustomerDA;

public class BillService {
    private AccountDA accountDA;
    private OtpService otpService;
    private CustomerDA customerDA;
    private BillDA billDA;

    public BillService(AccountDA accountDA, OtpService otpService, CustomerDA customerDA, BillDA billDA) {
        this.accountDA = accountDA;
        this.otpService = otpService;
        this.customerDA = customerDA;
        this.billDA = billDA;
    }

    public boolean accountBalanceValidation(Bill bill){
        String customer = CustomerData.getName();
        String nic = CustomerData.getNic();
        String fromAccountNumber = AccountData.getAccountNumber();

        boolean isValid = this.accountDA.accountBalanceValidation(fromAccountNumber, nic, bill.getAmount());

        return isValid;
    }

    public void sendOTP(String user, String nic){
        String phoneNumber = customerDA.retrievePhoneNumber(user, nic);
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
        boolean success = billDA.billTransaction(billOwner);
        if (!success) {
            return false;
        }
        return true;
    }
}

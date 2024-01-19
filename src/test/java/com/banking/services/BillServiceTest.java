package com.banking.services;

import com.banking.dataAccess.AccountDao;
import com.banking.dataAccess.BillDao;
import com.banking.models.Bill;
import com.banking.dataAccess.CustomerDao;
import com.banking.models.BillOwner;
import com.banking.models.AccountData;
import com.banking.models.CustomerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @InjectMocks private BillService underTest;
    @Mock private OtpService otpService;
    @Mock private AccountDao accountDao;
    @Mock private CustomerDao customerDao;
    @Mock private BillDao billDao;

    @Mock CustomerData customerData;
    @Mock AccountData accountData;
    @BeforeEach
    void setUp() {
        CustomerData.setName(null);
        CustomerData.setNic(null);
        AccountData.setAccountNumber(null);
    }


    @Test
    void Should_ReturnTrue_When_ValidAccountBalance() {
        // given
        String customer = "A A H Silva";
        String nic = "761007610V";
        String fromAccountNumber = "1002310023";
        CustomerData.setNic(nic);
        AccountData.setAccountNumber(fromAccountNumber);
        Bill bill = new Bill( "electricity bill","1200248000",5000);


        // when
        when(accountDao.accountBalanceValidation(anyString(),anyString(),anyInt()))
                .thenReturn(true);
        boolean actualOutput = underTest.accountBalanceValidation(bill);

        // then
        verify(accountDao, Mockito.times(1)).accountBalanceValidation(anyString(),anyString(),anyInt());
        assertTrue(actualOutput);
    }


    @Test
    void Should_ReturnFalse_When_InvalidAccountBalance() {
        // given
        int amount = 5000;
        String option = "electricity bill";
        String toAccountNumber = "1231239991";
        String fromAccountNumber = "1200248000";
        String nic = "985533212v";
        CustomerData.setNic(nic);
        AccountData.setAccountNumber(fromAccountNumber);
        Bill bill = new Bill(option,toAccountNumber,amount);

        // when
        when(accountDao.accountBalanceValidation(fromAccountNumber, nic, amount)).thenReturn(false);
        boolean actualOutput = underTest.accountBalanceValidation(bill);

        // then
        verify(accountDao, Mockito.times(1)).accountBalanceValidation(fromAccountNumber, nic, amount);
        assertFalse(actualOutput);
    }


    @Test
    void Should_CallOtpService_When_GivenUserDetail() {
        // given
        String user = "Anuki";
        String nic = "844442111v";

        // when
        when(customerDao.retrievePhoneNumber(user,nic)).thenReturn("0712020321");
        when(otpService.sentOTP("0712020321")).thenReturn(true);
        underTest.sendOTP(user,nic);

        // then (Assert)
        verify(customerDao,Mockito.times(1)).retrievePhoneNumber(user,nic);
        verify(otpService, Mockito.times(1)).sentOTP("0712020321");
    }

    @DisplayName("BillPaymentProceedTransaction - failing path 1")
    @Test
    void Should_ReturnFalse_When_AuthorizationFails() {
        // given
        int amount = 5000;
        int userGivenOtp = 221155;
        String billOption = "electricity bill";
        String accountNumber = "1200248000";
        String name = "A S Silva";
        String nic = "985533212v";
        CustomerData.setName(name);
        CustomerData.setNic(nic);
        AccountData.setAccountNumber(accountNumber);
        Bill bill = new Bill(billOption,accountNumber, amount);

        // when
        when(otpService.authorizeOTP(221155)).thenReturn(false);
        boolean actualOutput = underTest.authorizeAndProceedTransaction(userGivenOtp, bill);

        // then
        assertFalse(actualOutput);
        verify(otpService, Mockito.times(1)).authorizeOTP(userGivenOtp);
        verify(billDao, Mockito.times(0)).billTransaction(any());
    }

    @DisplayName("BillPaymentProceedTransaction - failing path 2")
    @Test
    void Should_ReturnFalse_When_TransactionFails() {
        // given
        int amount = 5000;
        int userGivenOtp = 221155;
        String billOption = "electricity bill";
        String accountNumber = "1200248000";
        Bill bill = new Bill(billOption, accountNumber,amount);

        // when
        when(otpService.authorizeOTP(221155)).thenReturn(true);
        when(billDao.billTransaction(any(BillOwner.class))).thenReturn(false);
        boolean actualOutput = underTest.authorizeAndProceedTransaction(userGivenOtp,bill);

        // then
        assertFalse(actualOutput);
        verify(otpService, Mockito.times(1)).authorizeOTP(eq(userGivenOtp));
        verify(billDao, Mockito.times(1)).billTransaction(any(BillOwner.class));
    }

    @DisplayName("BillPaymentProceedTransaction - happy path")
    @Test
    void Should_ReturnTrue_When_AuthorizeAndTransactionSuccess() {
        // given
        int amount = 5000;
        int userGivenOtp = 221155;
        String billOption = "electricity bill";
        String accountNumber = "1200248000";
        Bill bill = new Bill(billOption, accountNumber,amount);

        // when
        when(otpService.authorizeOTP(221155)).thenReturn(true);
        when(billDao.billTransaction(any(BillOwner.class))).thenReturn(true);
        boolean actualOutput = underTest.authorizeAndProceedTransaction(userGivenOtp,bill);

        // then
        assertTrue(actualOutput);
        verify(otpService, Mockito.times(1)).authorizeOTP(userGivenOtp);
        verify(billDao, Mockito.times(1)).billTransaction(any(BillOwner.class));
    }
}
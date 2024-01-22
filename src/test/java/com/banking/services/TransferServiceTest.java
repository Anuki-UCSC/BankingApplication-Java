package com.banking.services;

import com.banking.dataAccess.AccountDataAccess;
import com.banking.dataAccess.CustomerDataAccess;
import com.banking.dataAccess.TransferDataAccess;
import com.banking.models.Transfer;
import com.banking.models.TransferDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    private TransferService underTest;

    @Mock
    private TransferDataAccess transferDataAccess;
    @Mock
    private AccountDataAccess accountDataAccess;
    @Mock
    private CustomerDataAccess customerDataAccess;
    @Mock
    private OtpService otpService;



    @Test
    void Should_ReturnReceiverDetails_when_GivenAccountNumber() {
        // given
        String accountNumber = "0110006843";
        Transfer transferRec = new Transfer("0110006843", "A S Perera", "People's Bank" , 103);

        ///when
        when(transferDataAccess.getReceiverDetailsByAccountNumber()).thenReturn(transferRec);
        Transfer actualOutput = underTest.getReceiverDetails(accountNumber);

        // then
        verify(transferDataAccess, Mockito.times(1)).getReceiverDetailsByAccountNumber();
    }


    @Test
    void Should_CallOtpService_When_GivenUserDetails() {
        // given
        String user = "Anuki";
        String nic = "844442111v";

        // when
        when(customerDataAccess.retrievePhoneNumber(user,nic)).thenReturn("0712020321");
        when(otpService.sentOTP("0712020321")).thenReturn(true);
        underTest.sendOTP(user,nic);

        // then (Assert)
        verify(customerDataAccess,Mockito.times(1)).retrievePhoneNumber(user,nic);
        verify(otpService, Mockito.times(1)).sentOTP("0712020321");
    }


    @Test
    void Should_ReturnFalse_When_AuthorizationFails() {
        // given
        int userGivenOtp = 221155;
        String toAccountNumber = "1200248000";
        String accountName = "A S Perera";
        String bankName ="People's Bank";
        int branchCode = 122;
        int amount = 5000;
        String purpose = "Personal Reason";
        TransferDTO transferDto = new TransferDTO(toAccountNumber,accountName,bankName,branchCode,amount,purpose);

        // when
        when(otpService.authorizeOTP(221155)).thenReturn(false);
        boolean actualOutput = underTest.authorizeAndProceedTransaction(userGivenOtp, transferDto);

        // then
        assertFalse(actualOutput);
        verify(otpService, Mockito.times(1)).authorizeOTP(userGivenOtp);
        verify(transferDataAccess, Mockito.times(0)).transaction(transferDto);
    }
}
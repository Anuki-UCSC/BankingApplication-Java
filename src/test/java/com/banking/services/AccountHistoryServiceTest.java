package com.banking.services;

import com.banking.dataAccess.AccountHistoryDA;
import com.banking.models.AccountHistory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountHistoryServiceTest {

    @InjectMocks
    private AccountHistoryService underTest;

    @Mock
    private AccountHistoryDA accountHistoryDA;

    @Test
    void Should_ReturnListOfAccountHistory() {
        // given (Arrange)
        String accountId = "1002310023";
        ArrayList<AccountHistory> accountHistoryRecords = new ArrayList<>();
        accountHistoryRecords.add(new AccountHistory(1, new Date(), "Deposit", 100.0f, 100.0f));
        accountHistoryRecords.add(new AccountHistory(2, new Date(), "Withdrawal", 50.0f, 50.0f));

        // when (act)
        when(accountHistoryDA.getAccountHistory(accountId)).thenReturn(accountHistoryRecords);
        ArrayList<AccountHistory> actualOutput = underTest.getAccountHistory(accountId);

        // then (Assert)
        verify(accountHistoryDA, Mockito.times(1)).getAccountHistory(accountId);
        assertEquals(accountHistoryRecords, actualOutput);

    }
}
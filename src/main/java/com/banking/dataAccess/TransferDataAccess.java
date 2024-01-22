package com.banking.dataAccess;

import com.banking.models.Transfer;
import com.banking.models.TransferDTO;

public class TransferDataAccess {
    public Transfer getReceiverDetailsByAccountNumber() {
        Transfer transfer = new Transfer();
        transfer.setAccountName("A S Perera");
        transfer.setBankName("People's Bank");
        transfer.setBranchCode(103);

        return transfer;
    }

    public boolean transaction(TransferDTO transferDTO) {

        return true;
    }
}

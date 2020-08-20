package com.timplant.services.transaction;


import com.timplant.models.Sum;
import com.timplant.models.Transaction;

import java.util.ArrayList;

public interface TransactionService {
    Transaction getTransactionById(Long transactionId) throws TransactionNotFoundException;

    Transaction createNewTransaction(double amount, String type);

    Transaction createNewTransaction(double amount, String type, long parentId);

    Sum calculateSum(Transaction transaction);

    ArrayList<Long> getTransactionsByType(String type);
}

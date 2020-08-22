package com.timplant.services.transaction;


import com.timplant.models.Sum;
import com.timplant.models.Transaction;
import com.timplant.services.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.annotation.*;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Component
public class TransactionServiceImpl implements TransactionService {

    protected TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(@Qualifier("transaction_repository_inmemory")
                                  TransactionRepository repository) {
        this.repository = repository;
    }

    @CacheResult(cacheName = "roles")
    public Transaction getTransactionById(@CacheKey Long transactionId) throws TransactionNotFoundException {

        Transaction transaction = this.repository.getTransactionById(transactionId);

        if (null == transaction) {
            throw new TransactionNotFoundException("Transaction with id " + transactionId + " not found");
        }

        return transaction;
    }

    protected Transaction saveTransactionToRepository(Transaction transaction) {
        return this.repository.saveTransaction(transaction);
    }

    public Transaction createNewTransaction(double amount, String type) {
        Transaction transaction = new Transaction(amount, type);
        return this.saveTransactionToRepository(transaction);
    }

    public Transaction createNewTransaction(double amount, String type, long parentId) throws TransactionNotFoundException {
        /**
         * Get parent transaction
         */
        Transaction parentTransaction = this.getTransactionById(parentId);
        Transaction transaction = new Transaction(amount, type, parentTransaction);
        return this.saveTransactionToRepository(transaction);
    }

    public Sum calculateSum(Transaction transaction) {
        double sum = 0;

        Map<Long, Transaction> children = repository.getTransactionByCriteria("parentId", transaction.getId());
        Iterator it = children.values().iterator();

        while (it.hasNext()) {
            sum += ((Transaction) it.next()).getAmount();
        }

        return new Sum(sum);
    }

    public ArrayList<Long> getTransactionsByType(String type) {

        ArrayList<Long> transactions = new ArrayList<>();
        Map<Long, Transaction> children = repository.getTransactionByCriteria("type", type);

        children.forEach((key, object) -> {
            transactions.add(object.getId());
        });

        return transactions;
    }
}

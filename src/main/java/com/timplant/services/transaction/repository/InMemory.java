package com.timplant.services.transaction.repository;


import com.timplant.models.Transaction;
import com.timplant.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.util.Map;

@Component("transaction_repository_inmemory")
public class InMemory extends TransactionRepository {

    protected Storage<Transaction> storage;

    @Autowired
    public InMemory(@Qualifier("storage_inmemory") Storage<Transaction> storage) {
        this.storage = storage;
    }

    @Override
    @CacheResult
    public Transaction getTransactionById(long transactionId) {
        return this.storage.getObjectByPrimaryIndex(transactionId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return this.storage.saveObject(transaction);
    }

    @Override
    public Map<Long, Transaction> getAllTransactions() {
        return storage.getAllObjects();
    }

    @Override
    public Map<Long, Transaction> getTransactionByCriteria(String key, Object value) {
        return storage.getObjectsByCriteria(key, value);
    }
}

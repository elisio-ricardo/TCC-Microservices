package com.example.kafkaconsumer.services;

import com.example.kafkaconsumer.model.Transaction;
import com.example.kafkaconsumer.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}

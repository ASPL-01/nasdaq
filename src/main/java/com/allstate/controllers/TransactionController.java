package com.allstate.controllers;

import com.allstate.entities.Transaction;
import com.allstate.exceptions.TransactionException;
import com.allstate.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/buy/{userId}/{symbol}/{quantity}", method = RequestMethod.POST)
    public Transaction buy(@PathVariable int userId, @PathVariable String symbol, @PathVariable int quantity) throws TransactionException{
        return this.transactionService.buy(userId, symbol, quantity);
    }

    @RequestMapping(value = "/sell/{userId}/{symbol}/{quantity}", method = RequestMethod.POST)
    public Transaction sell(@PathVariable int userId, @PathVariable String symbol, @PathVariable int quantity) throws TransactionException{
        return this.transactionService.sell(userId, symbol, quantity);
    }
}

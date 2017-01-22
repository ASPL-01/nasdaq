package com.allstate.services;

import com.allstate.entities.Transaction;
import com.allstate.entities.User;
import com.allstate.enums.Action;
import com.allstate.exceptions.TransactionException;
import com.allstate.models.Stock;
import com.allstate.repositories.ITransactionRepository;
import com.allstate.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {
    private ITransactionRepository transactionRepository;
    private IUserRepository userRepository;
    private QuoteService quoteService;

    @Autowired
    public void setTransactionRepository(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public Transaction buy(int userId, String symbol, int quantity) throws TransactionException{
        User user = this.userRepository.findOne(userId);
        Stock stock = quoteService.quote(symbol);
        double purchasePrice = stock.getLastPrice() * quantity;
        if(user.getBalance() >= purchasePrice){
            double balance = user.getBalance() - purchasePrice;
            user.setBalance(balance);
            Transaction transaction = new Transaction(Action.BUY, symbol, quantity, purchasePrice, user);
            transaction.setUser(user);
            this.transactionRepository.save(transaction);
            this.userRepository.save(user);
            return transaction;
        }else{
            throw new TransactionException("Not enough available funds.");
        }
    }

    public int countSharesPurchasedOrSoldBySymbol(int userId, Action action, String symbol){
        Optional<BigDecimal> count = this.transactionRepository.countSharesPurchasedOrSoldBySymbol(userId, action.toString(), symbol);
        return count.isPresent() ? count.get().intValue() : 0;
    }
}

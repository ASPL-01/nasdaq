package com.allstate.services;

import com.allstate.entities.User;
import com.allstate.enums.Funds;
import com.allstate.models.Position;
import com.allstate.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private IUserRepository userRepository;
    private TransactionService transactionService;
    private QuoteService quoteService;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public User create(String email){
        return this.userRepository.save(new User(email));
    }

    public User update(User user){
        return this.userRepository.save(user);
    }

    public User findUserById(int id){
        Optional<User> oUser = Optional.ofNullable(this.userRepository.findOne(id));
        if(oUser.isPresent()) {
            return oUser.get();
        }else{
            throw new IllegalArgumentException("User ID not found");
        }
    }

    public Iterable<User> findAll(){
        return this.userRepository.findAll();
    }

    public double changeFunds(int id, double amount, Funds funds){
        User user = this.findUserById(id);
        double balance = user.getBalance();
        balance = funds == Funds.DEPOSIT ? balance + amount : balance - amount;
        user.setBalance(balance);
        this.userRepository.save(user);
        return balance;
    }

    public Position getPosition(int id) {
        User user = this.findUserById(id);
        Position position = new Position();
        position.setCash(user.getBalance());
        return this.transactionService.getUniqueStockSymbols(id)
                .stream()
                .collect(Collectors.toMap(k -> k, k -> transactionService.countAvailableShares(id, k)))
                .entrySet()
                .stream()
                .reduce(position, (pos, map) -> {
                    pos.addPositionDetail(map.getKey(),
                            map.getValue(),
                            map.getValue() * quoteService.quote(map.getKey()).getLastPrice());
                    return pos;
                }, (a, b) -> null);
    }
}

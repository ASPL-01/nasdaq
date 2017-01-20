package com.allstate.services;

import com.allstate.entities.User;
import com.allstate.enums.Funds;
import com.allstate.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private IUserRepository repository;

    @Autowired
    public void setRepository(IUserRepository repository) {
        this.repository = repository;
    }

    public User create(String email){
        return this.repository.save(new User(email));
    }

    public double changeFunds(int id, double amount, Funds funds){
        User user = this.findUserById(id);
        double balance = user.getBalance();
        balance = funds == Funds.DEPOSIT ? balance + amount : balance - amount;
        user.setBalance(balance);
        this.repository.save(user);
        return balance;
    }

    public User findUserById(int id){
        Optional<User> oUser = Optional.ofNullable(this.repository.findOne(id));
        if(oUser.isPresent()) {
            return oUser.get();
        }else{
            throw new IllegalArgumentException("User ID not found");
        }
    }
}

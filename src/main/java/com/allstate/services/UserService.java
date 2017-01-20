package com.allstate.services;

import com.allstate.entities.User;
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

    public double deposit(int id, double amount){
        Optional<User> oUser = Optional.ofNullable(this.repository.findOne(id));
        if(oUser.isPresent()){
            User user = oUser.get();
            double balance = user.getBalance();
            balance += amount;
            user.setBalance(balance);
            this.repository.save(user);
            return balance;
        }
        throw new IllegalArgumentException("User ID not found");
    }
}

package com.allstate.services;

import com.allstate.entities.User;
import com.allstate.enums.Funds;
import com.allstate.models.Position;
import com.allstate.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
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

    public Position getPosition(int id){
        User user = this.findUserById(id);
        return new Position();
    }
}

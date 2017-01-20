package com.allstate.services;

import com.allstate.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User create(String email){
        throw new IllegalArgumentException("Bad Stuff");
    };
}

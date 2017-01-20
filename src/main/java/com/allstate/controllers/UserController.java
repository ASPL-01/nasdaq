package com.allstate.controllers;

import com.allstate.entities.User;
import com.allstate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public User create(@RequestBody Map<String, String> json){
        return this.service.create(json.get("email"));
    }

    @RequestMapping(value = "/{id}/deposit/{amount}", method = RequestMethod.POST)
    public Map<String, Double> deposit(@PathVariable int id, @PathVariable double amount){
        Map<String, Double> json = new HashMap<>();
        double balance = this.service.deposit(id, amount);
        json.put("balance", balance);
        return json;
    }
}

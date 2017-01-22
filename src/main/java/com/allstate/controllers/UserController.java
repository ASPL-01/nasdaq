package com.allstate.controllers;

import com.allstate.entities.User;
import com.allstate.enums.Funds;
import com.allstate.models.Position;
import com.allstate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public User create(@RequestBody Map<String, String> json){
        return this.userService.create(json.get("email"));
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Iterable<User> findAll(){
        return this.userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable int id){
        return this.userService.findUserById(id);
    }

    @RequestMapping(value = "/{id}/deposit/{amount}", method = RequestMethod.PUT)
    public Map<String, Double> deposit(@PathVariable int id, @PathVariable double amount){
        Map<String, Double> json = new HashMap<>();
        double balance = this.userService.changeFunds(id, amount, Funds.DEPOSIT);
        json.put("balance", balance);
        return json;
    }

    @RequestMapping(value = "/{id}/withdraw/{amount}", method = RequestMethod.PUT)
    public Map<String, Double> withdraw(@PathVariable int id, @PathVariable double amount){
        Map<String, Double> json = new HashMap<>();
        double balance = this.userService.changeFunds(id, amount, Funds.WITHDRAW);
        json.put("balance", balance);
        return json;
    }

    @RequestMapping(value = "/{id}/position", method = RequestMethod.GET)
    public Position getPosition(@PathVariable int id){
        return this.userService.getPosition(id);
    }
}

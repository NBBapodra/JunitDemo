package com.example.JunitDemo.controller;


import com.example.JunitDemo.Services.UserService;
import com.example.JunitDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/save")
    public User saveUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/getUsers")
    public List<User> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/getUserByAddress/{address}")
    public List<User> findUserByAddress(@PathVariable String address) {
        return service.getUserbyAddress(address);
    }

    @DeleteMapping(value="/remove")
    public User removeUser(@RequestBody User user) {
        service.deleteUser(user);
        return user;
    }
}

package com.smartpay.smartpay.controller;

import com.smartpay.smartpay.entity.User;
import com.smartpay.smartpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        return userService.create(user);
    }

}

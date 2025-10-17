package com.smartpay.controller;

import com.smartpay.dto.ApiResponse;
import com.smartpay.entity.User;
import com.smartpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<User>> getAll(){
        List<User> users =  userService.getAll();
        return ApiResponse.success("Users fetched successfully",users);
    }

}

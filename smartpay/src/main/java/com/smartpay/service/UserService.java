package com.smartpay.smartpay.service;

import com.smartpay.smartpay.entity.User;
import com.smartpay.smartpay.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService (UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User create (User user) {
        if (userRepo.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email already registered" );
        return userRepo.save(user);
    }
}
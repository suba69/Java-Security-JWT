package com.example.security1337.service;

import com.example.security1337.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getByUsername(String username);

}

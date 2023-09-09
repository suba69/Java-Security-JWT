package com.example.security1337.dto;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;

}

package com.example.security1337.controller;

import com.example.security1337.dto.BaseResponse;
import com.example.security1337.dto.JwtRequest;
import com.example.security1337.dto.JwtResponse;
import com.example.security1337.dto.UserRegistrationDto;
import com.example.security1337.service.AuthService;
import com.example.security1337.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.security1337.utils.ResponseUtils.CREATION_MESSAGE;
import static com.example.security1337.utils.ResponseUtils.getSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        String token = authService.getToken(jwtRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<BaseResponse> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto) throws InstantiationException, IllegalAccessException {
        userService.save(userRegistrationDto);
        return ResponseEntity.ok(getSuccessResponse(CREATION_MESSAGE, "user"));
    }

}

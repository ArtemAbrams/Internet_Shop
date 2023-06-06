package com.example.first.controller;

import com.example.first.data.AuthenticationRequest;
import com.example.first.data.AuthenticationResponse;
import com.example.first.auth.impl.AuthenticationServiceImpl;
import com.example.first.data.RegisterRequest;
import lombok.RequiredArgsConstructor;
;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AuthController {
    private final AuthenticationServiceImpl service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
       return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}

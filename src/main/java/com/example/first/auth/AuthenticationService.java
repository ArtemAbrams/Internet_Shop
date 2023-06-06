package com.example.first.auth;

import com.example.first.data.AuthenticationRequest;
import com.example.first.data.AuthenticationResponse;
import com.example.first.data.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request);
}

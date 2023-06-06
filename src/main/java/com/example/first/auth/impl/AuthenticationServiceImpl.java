package com.example.first.auth.impl;

import com.example.first.auth.AuthenticationService;
import com.example.first.data.AuthenticationRequest;
import com.example.first.data.AuthenticationResponse;
import com.example.first.data.RegisterRequest;
import com.example.first.config.JwtService;
import com.example.first.entity.User;
import com.example.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            final var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            manager.authenticate(authenticationToken);
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + request.getEmail() + " was not Found"));
            var jwt = jwtService.generateToken(user);
            return AuthenticationResponse
                    .builder()
                    .token(jwt)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AuthenticationResponse();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}

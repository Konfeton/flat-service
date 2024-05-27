package com.onkonfeton.flatservice.auth.service;

import com.onkonfeton.flatservice.auth.dto.LoginRequest;
import com.onkonfeton.flatservice.auth.dto.JwtResponse;
import com.onkonfeton.flatservice.auth.dto.RegistrationRequest;
import com.onkonfeton.flatservice.user.model.User;
import com.onkonfeton.flatservice.user.model.enums.Role;
import com.onkonfeton.flatservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtResponse register(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(Role.USER)
                .isActive(true)
                .build();
        userService.save(user);
        String token = jwtService.generateToken(user);
        return new JwtResponse(token);
    }

    public JwtResponse authenticate(LoginRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        User user = userService.findByEmail(authRequest.getEmail()).orElseThrow();
        if (!user.isActive()){
            throw new UserNotActiveException("Пользователь заблокирован");
        }
        String token = jwtService.generateToken(user);
        return new JwtResponse(token);

    }
}
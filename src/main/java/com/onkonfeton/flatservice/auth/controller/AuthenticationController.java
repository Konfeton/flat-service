package com.onkonfeton.flatservice.auth.controller;

import com.onkonfeton.flatservice.auth.dto.LoginRequest;
import com.onkonfeton.flatservice.auth.dto.JwtResponse;
import com.onkonfeton.flatservice.auth.dto.RegistrationRequest;
import com.onkonfeton.flatservice.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/registration")
  public ResponseEntity<JwtResponse> registration(
      @RequestBody RegistrationRequest registrationRequest
  ) {
    return ResponseEntity.ok(authenticationService.register(registrationRequest));
  }
  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(
      @RequestBody LoginRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

}

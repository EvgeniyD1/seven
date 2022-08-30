package com.example.seven.controller;

import com.example.seven.request.AuthRequest;
import com.example.seven.request.SingUpRequest;
import com.example.seven.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody SingUpRequest request){
        if (userService.findByUsername(request.getUsername())!=null){
            return ResponseEntity.badRequest().body("A user with the same name already exists!");
        }
        userService.createAccount(request);
        return ResponseEntity.ok("Successful registration!");
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}

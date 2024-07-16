package com.foro_med.angel.foro_med.angel.controller;


import com.foro_med.angel.foro_med.angel.domain.user.AuthenticationUserData;
import com.foro_med.angel.foro_med.angel.domain.user.User;
import com.foro_med.angel.foro_med.angel.infra.security.JwtData;
import com.foro_med.angel.foro_med.angel.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public JwtData login(@RequestBody @Valid AuthenticationUserData authenticationUserData) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationUserData.login(),
                authenticationUserData.password()
        );

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            String token = tokenService.generateToken((User) authentication.getPrincipal());
            return new JwtData(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid login credentials", e);
        }
    }
}

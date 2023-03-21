package com.example.cloudservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cloudservice.dto.LoginInRequest;
import com.example.cloudservice.dto.LoginInResponse;
import com.example.cloudservice.service.AuthorizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthorizationService authorizationService;

    public LoginController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public LoginInResponse login(@Valid @RequestBody LoginInRequest loginInRequest) {
        return authorizationService.login(loginInRequest);
    }
}

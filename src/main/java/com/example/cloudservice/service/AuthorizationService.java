package com.example.cloudservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.cloudservice.dto.LoginInRequest;
import com.example.cloudservice.dto.LoginInResponse;
import com.example.cloudservice.entities.TokenEntity;
import com.example.cloudservice.entities.UserEntity;
import com.example.cloudservice.exceptions.AuthorizationException;
import com.example.cloudservice.exceptions.BadCredentialsException;
import com.example.cloudservice.repository.TokenRepository;
import com.example.cloudservice.repository.UserRepository;

import java.util.Random;

@Service
public class AuthorizationService {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final Random random = new Random();

    public AuthorizationService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public LoginInResponse login(LoginInRequest loginInRequest) {
        final String loginFromRequest = loginInRequest.getLogin();
        final UserEntity user = userRepository.findById(loginFromRequest).orElseThrow(() ->
                new BadCredentialsException("User with login " + loginFromRequest + " not found"));

        if (!user.getPassword().equals(loginInRequest.getPassword())) {
            throw new BadCredentialsException("Incorrect password for user " + loginFromRequest);
        }
        final String authToken = String.valueOf(random.nextLong());
        tokenRepository.save(new TokenEntity(authToken));
        logger.info("User " + loginFromRequest + " entered with token " + authToken);
        return new LoginInResponse(authToken);
    }

    public void logout(String authToken) {
        tokenRepository.deleteById(authToken.split(" ")[1].trim());
    }

    public void checkToken(String authToken) {
        if (!tokenRepository.existsById(authToken.split(" ")[1].trim())) {
            throw new AuthorizationException();
        }
    }
}

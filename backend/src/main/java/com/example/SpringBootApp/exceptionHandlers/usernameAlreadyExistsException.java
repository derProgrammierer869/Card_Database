package com.example.SpringBootApp.exceptionHandlers;

import com.example.SpringBootApp.Service.UserService;

public class usernameAlreadyExistsException extends RuntimeException {
    public usernameAlreadyExistsException(String username) {
        super("Username already exists: " + username);
    }
}

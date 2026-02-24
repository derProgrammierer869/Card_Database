package com.example.SpringBootApp.SecuritySetup;

import com.example.SpringBootApp.exceptionHandlers.usernameAlreadyExistsException;
import org.springframework.stereotype.Service;
import com.example.SpringBootApp.Repository.UserRepository;
import com.example.SpringBootApp.Repository.CardsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.SpringBootApp.UserEntity.User;
import com.example.SpringBootApp.LoginDTOs.*;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {
        if(userRepo.existsByUsername(request.username())) {
            throw new IllegalStateException("Username already exists!");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        userRepo.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepo.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}


package com.dev.mealplanner.user.service;

import com.dev.mealplanner.auth.TokenService;
import com.dev.mealplanner.user.domain.LoginTO;
import com.dev.mealplanner.user.domain.RegisterTO;
import com.dev.mealplanner.user.domain.User;
import com.dev.mealplanner.user.domain.UserMapper;
import com.dev.mealplanner.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    private TokenService tokenService;

    private AuthenticationManager authenticationManager;

    public Map<String, String> register(RegisterTO to) {
        boolean userExists = userRepository.findUserByEmail(to.email()).isPresent();

        if (userExists) throw new RuntimeException("User already exists!");

        User user = new User();
        user.setName(to.name());
        user.setEmail(to.email());
        user.setPassword(passwordEncoder.encode(to.password()));

        userRepository.save(user);

        return Map.of("status", "User created successfully!");
    }

    public String getToken(LoginTO to) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(to.email(), to.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        System.out.println("Authentication details: " + auth);
        User authenticatedUser = (User) auth.getPrincipal();
        return tokenService.generateToken(authenticatedUser);

    }

    public Map<String, String> changePassword(String newPassword) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("User not Found!"));

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return Map.of("status", "password changed successfully!");
    }
}

package com.dev.mealplanner.user.service;

import com.dev.mealplanner.auth.TokenService;
import com.dev.mealplanner.user.domain.*;
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

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public Map<String, String> register(RegisterTO to) {
        boolean userExists = userRepository.findUserByEmail(to.email()).isPresent();

        if (userExists) throw new RuntimeException("User already exists!");

        User user = User.builder()
                .name(to.name())
                .email(to.email())
                .weight(to.weight())
                .height(to.height())
                .age(to.age())
                .gender(to.gender())
                .activityLevel(to.activityLevel())
                .password(passwordEncoder.encode(to.password())).build();

        user.setMetabolicRate(calculateBmr(user));
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

    public UserTO getUserInfo() {
      User currentUser = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("User not Found!"));
      return userMapper.sourceToDto(currentUser);
    }

    public Double calculateBmr(User user) {
        double bmr = 0.0;

        if (user.getGender() == Gender.MALE) {
            bmr = (user.getWeight() * 10) + (user.getHeight() * 6.25) - (user.getAge() * 5) + 5;
        }

        if (user.getGender() == Gender.FEMALE) {
            bmr = (user.getWeight() * 10) + (user.getHeight() * 6.25) - (user.getAge() * 5) - 161;
        }

        return bmr * getActivityFactor(user.getActivityLevel());
    }

    public Double getActivityFactor(ActivityLevel activityLevel) {

        return switch(activityLevel) {
            case SEDENTARY -> 1.2;
            case LIGHTLY -> 1.375;
            case MODERATELY -> 1.55;
            case VERY_ACTIVE -> 1.725;
            case SUPER_ACTIVE -> 1.9;
        };
    }
}

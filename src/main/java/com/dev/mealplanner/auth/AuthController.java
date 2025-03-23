package com.dev.mealplanner.auth;

import com.dev.mealplanner.user.domain.LoginTO;
import com.dev.mealplanner.user.domain.RegisterTO;
import com.dev.mealplanner.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private UserService userService;

    @GetMapping("token")
    public ResponseEntity<String> login(@RequestBody() LoginTO to) {
        return ResponseEntity.ok(userService.getToken(to));
    }

    @PostMapping("register")
    public ResponseEntity<Map<String,String>> register(@RequestBody() RegisterTO to) {
        return ResponseEntity.ok(userService.register(to));
    }

    @PutMapping("changePassword")
    public ResponseEntity<Map<String,String>> changePassword(@RequestParam("newPassword") String newPassword) {
        return ResponseEntity.ok(userService.changePassword(newPassword));
    }
}

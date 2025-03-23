package com.dev.mealplanner.user.controller;

import com.dev.mealplanner.user.domain.UserTO;
import com.dev.mealplanner.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("getInfo")
    public ResponseEntity<UserTO> getUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }

}

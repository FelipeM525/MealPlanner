package com.dev.mealplanner.user.controller;

import com.dev.mealplanner.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;



}

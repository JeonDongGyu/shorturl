package com.shorturl.presentation;

import com.shorturl.application.UserService;
import com.shorturl.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest requestDto){
        User user = userService.register(requestDto.getEmail(), requestDto.getPassword(), requestDto.getNickname());

        return ResponseEntity.ok(new UserResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserLoginRequest requestDto){
        User user = userService.login(requestDto.getEmail(),requestDto.getPassword());

        return ResponseEntity.ok(new UserResponse(user));
    }
}

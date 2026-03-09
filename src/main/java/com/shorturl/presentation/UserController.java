package com.shorturl.presentation;

import com.shorturl.application.UserService;
import com.shorturl.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRegisterRequestDto requestDto){
        User user = userService.register(requestDto.getEmail(), requestDto.getPassword(), requestDto.getNickname());

        return ResponseEntity.ok(new UserResponseDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody UserLoginRequestDto requestDto){
        User user = userService.login(requestDto.getEmail(),requestDto.getPassword());

        return ResponseEntity.ok(new UserResponseDto(user));
    }
}

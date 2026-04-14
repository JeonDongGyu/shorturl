package com.shorturl.presentation;

import com.shorturl.application.UserService;
import com.shorturl.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "회원", description = "회원가입 · 로그인")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "이메일, 비밀번호, 닉네임으로 회원을 등록합니다.")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest requestDto){
        User user = userService.register(requestDto.getEmail(), requestDto.getPassword(), requestDto.getNickname());

        return ResponseEntity.ok(new UserResponse(user));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserLoginRequest requestDto){
        User user = userService.login(requestDto.getEmail(),requestDto.getPassword());

        return ResponseEntity.ok(new UserResponse(user));
    }
}

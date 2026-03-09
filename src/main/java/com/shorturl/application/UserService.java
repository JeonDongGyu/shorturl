package com.shorturl.application;

import com.shorturl.domain.User;
import com.shorturl.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(String email, String password, String nickname){
        if (userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        User user = new User(email,password,nickname);
        return userRepository.save(user);
    }

    public User login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return user;
    }

}

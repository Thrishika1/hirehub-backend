package com.hirehub.auth.service;

import com.hirehub.auth.dto.RegisterRequest;
import com.hirehub.auth.dto.RegisterResponse;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public RegisterResponse register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setEnabled(true);
        user.setEmailVerified(false);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getId(), savedUser.getEmail(),
                savedUser.getRole());
    }
}

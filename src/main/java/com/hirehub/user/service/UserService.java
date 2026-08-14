package com.hirehub.user.service;

import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.user.dto.UserResponse;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        )
                );

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
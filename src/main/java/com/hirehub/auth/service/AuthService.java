package com.hirehub.auth.service;

import com.hirehub.auth.dto.LoginRequest;
import com.hirehub.auth.dto.LoginResponse;
import com.hirehub.auth.dto.RegisterRequest;
import com.hirehub.auth.dto.RegisterResponse;
import com.hirehub.security.JwtService;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setEmail(request.getEmail());
        //user.setPassword(request.getPassword()); -> this stores raw password
        //hash password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setEnabled(true);
        user.setEmailVerified(false);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getId(), savedUser.getEmail(),
                savedUser.getRole());
    }

    public LoginResponse login(LoginRequest request){

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: "
                                        + request.getEmail()
                        )
                );

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return new LoginResponse(user.getId(), user.getEmail(),user.getRole(), token, "Bearer");
    }
}

package com.hirehub.auth.dto;

import com.hirehub.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String email;
    private Role role;

    private String accessToken;
    private String tokenType;
}

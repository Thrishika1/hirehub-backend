package com.hirehub.auth.dto;

import com.hirehub.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String email;
    private Role role;
}

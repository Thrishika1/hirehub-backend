package com.hirehub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruiterProfileResponse {

    private Long id;
    private Long userId;

    private Long companyId;
    private String companyName;

    private String firstName;
    private String lastName;
    private String phone;
    private String designation;
}
package com.hirehub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CandidateProfileResponse {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String headline;
    private String about;
    private String city;
    private String state;
    private String country;
    private String profilePictureUrl;
    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;
}
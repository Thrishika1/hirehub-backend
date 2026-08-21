package com.hirehub.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyResponse {

    private Long id;
    private String name;
    private String description;
    private String website;
    private String email;
    private String phone;
    private String industry;
    private String companySize;
    private Integer foundedYear;
    private String city;
    private String state;
    private String country;
    private String logoUrl;
    private boolean verified;
}
package com.hirehub.skill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSkillRequest {

    @NotBlank
    @Size(max = 100)
    private String name;
}

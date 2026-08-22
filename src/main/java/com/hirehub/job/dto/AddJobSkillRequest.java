package com.hirehub.job.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddJobSkillRequest {

    @NotNull
    private Long skillId;

    private Boolean required = true;
}
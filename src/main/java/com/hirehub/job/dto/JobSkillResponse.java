package com.hirehub.job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobSkillResponse {

    private Long id;

    private Long jobId;

    private Long skillId;

    private String skillName;

    private boolean required;
}
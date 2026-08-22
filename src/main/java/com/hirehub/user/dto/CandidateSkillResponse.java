package com.hirehub.user.dto;

import com.hirehub.common.enums.ProficiencyLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CandidateSkillResponse {

    private Long id;

    private Long candidateId;

    private Long skillId;

    private String skillName;

    private Integer yearsOfExperience;

    private ProficiencyLevel proficiency;
}
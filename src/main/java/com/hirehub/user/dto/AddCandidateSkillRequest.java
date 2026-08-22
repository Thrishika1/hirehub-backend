package com.hirehub.user.dto;

import com.hirehub.common.enums.ProficiencyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCandidateSkillRequest {

    @NotNull
    private Long skillId;

    @Min(0)
    private Integer yearsOfExperience;

    @NotNull
    private ProficiencyLevel proficiency;
}
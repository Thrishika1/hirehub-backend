package com.hirehub.job.dto;

import com.hirehub.common.enums.EmploymentType;
import com.hirehub.common.enums.WorkMode;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateJobRequest {

    @NotNull
    private Long recruiterId;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private EmploymentType employmentType;

    @Min(0)
    private Integer experienceMin;

    @Min(0)
    private Integer experienceMax;

    @Min(0)
    private Long salaryMin;

    @Min(0)
    private Long salaryMax;

    @Size(max = 255)
    private String location;

    @NotNull
    private WorkMode workMode;

    @Min(1)
    private Integer vacancies;

    @Future
    private LocalDateTime applicationDeadline;
}
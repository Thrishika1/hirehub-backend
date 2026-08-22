package com.hirehub.job.dto;

import com.hirehub.common.enums.EmploymentType;
import com.hirehub.common.enums.JobStatus;
import com.hirehub.common.enums.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JobResponse {

    private Long id;

    private Long companyId;
    private String companyName;

    private Long postedById;

    private String title;
    private String description;

    private EmploymentType employmentType;

    private Integer experienceMin;
    private Integer experienceMax;

    private Long salaryMin;
    private Long salaryMax;

    private String location;

    private WorkMode workMode;

    private Integer vacancies;

    private JobStatus status;

    private LocalDateTime applicationDeadline;
}
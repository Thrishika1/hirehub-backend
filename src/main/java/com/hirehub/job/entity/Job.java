package com.hirehub.job.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.common.enums.EmploymentType;
import com.hirehub.common.enums.JobStatus;
import com.hirehub.common.enums.WorkMode;
import com.hirehub.company.entity.Company;
import com.hirehub.user.entity.RecruiterProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "company_id",
        nullable = false
    )
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "posted_by",
        nullable = false
    )
    private RecruiterProfile postedBy;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EmploymentType employmentType;

    private Integer experienceMin;

    private Integer experienceMax;

    private Long salaryMin;

    private Long salaryMax;

    @Column(nullable = false, length = 150)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private WorkMode workMode;

    @Column(nullable = false)
    private Integer vacancies = 1;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private JobStatus status = JobStatus.DRAFT;

    private LocalDateTime applicationDeadline;
}

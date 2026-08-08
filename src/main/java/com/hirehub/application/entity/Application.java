package com.hirehub.application.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.common.enums.ApplicationStatus;
import com.hirehub.job.entity.Job;
import com.hirehub.user.entity.CandidateProfile;
import com.hirehub.user.entity.Resume;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "applications",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "candidate_job",
            columnNames = {"candidate_id", "job_id"}
        )
    }
)
@Getter
@Setter
public class Application extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateProfile candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @Column(length = 2000)
    private String coverLetter;

    @Column(nullable = false)
    private LocalDateTime appliedAt;
}

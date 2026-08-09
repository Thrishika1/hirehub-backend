package com.hirehub.job.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.user.entity.CandidateProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "saved_jobs",
    uniqueConstraints = {
        @UniqueConstraint(
            name ="uk_saved_job_candidate",
            columnNames = {"candidate_id", "job_id"}
        )
    }
)
@Getter
@Setter
public class SavedJob extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateProfile candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(nullable = false)
    private LocalDateTime savedAt;
}

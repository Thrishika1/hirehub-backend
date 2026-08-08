package com.hirehub.job.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.skill.entity.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "job_skills",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_job_skill",
            columnNames = {"job_id", "skill_id"}
        )
    }
)
@Getter
@Setter
public class JobSkill extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    private boolean required = true;
}

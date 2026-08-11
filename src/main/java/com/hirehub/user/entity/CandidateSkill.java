package com.hirehub.user.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.skill.entity.Skill;
import com.hirehub.common.enums.ProficiencyLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "candidate_skills",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_candidate_skill",
            columnNames = {"candidate_id", "skill_id"}
        )
    }
)
@Getter
@Setter
public class CandidateSkill extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateProfile candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProficiencyLevel proficiency;
}

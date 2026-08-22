package com.hirehub.user.repository;

import com.hirehub.user.entity.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill,Long> {

    boolean existsByCandidateIdAndSkillId(
            Long candidateId,
            Long skillId
    );
}

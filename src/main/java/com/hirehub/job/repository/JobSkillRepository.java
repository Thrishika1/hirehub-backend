package com.hirehub.job.repository;

import com.hirehub.job.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {

    boolean existsByJobIdAndSkillId(
            Long jobId,
            Long skillId
    );
}
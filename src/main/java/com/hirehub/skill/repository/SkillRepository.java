package com.hirehub.skill.repository;

import com.hirehub.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,Long> {

    boolean existsByName(String name);
}

package com.hirehub.skill.service;

import com.hirehub.skill.dto.CreateSkillRequest;
import com.hirehub.skill.dto.SkillResponse;
import com.hirehub.skill.entity.Skill;
import com.hirehub.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillResponse createSkill(CreateSkillRequest request){

        // 1. check for duplicate skill
        if(skillRepository.existsByName(request.getName())){
            throw new IllegalArgumentException("Skill already exists with name: "+request.getName());
        }

        // 2. create skill
        Skill skill = new Skill();
        skill.setName(request.getName());

        Skill savedSkill = skillRepository.save(skill);

        return new SkillResponse(savedSkill.getId(), savedSkill.getName());

    }
}

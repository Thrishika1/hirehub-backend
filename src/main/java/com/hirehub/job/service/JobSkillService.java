package com.hirehub.job.service;

import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.job.dto.AddJobSkillRequest;
import com.hirehub.job.dto.JobSkillResponse;
import com.hirehub.job.entity.Job;
import com.hirehub.job.entity.JobSkill;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.job.repository.JobSkillRepository;
import com.hirehub.skill.entity.Skill;
import com.hirehub.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobSkillService {

    private final JobSkillRepository jobSkillRepository;

    private final JobRepository jobRepository;

    private final SkillRepository skillRepository;

    public JobSkillResponse addSkill(Long jobId, AddJobSkillRequest request){

        // 1. Find job
        Job job = jobRepository.findById(jobId)
            .orElseThrow(
            () -> new ResourceNotFoundException("Job not found with id: "+jobId)
        );

        // 2. Find skill
        Skill skill = skillRepository.findById(request.getSkillId())
            .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Skill not found with id: " + request.getSkillId()
                )
        );

        // 3. Prevent duplicate skill for same job
        if(jobSkillRepository.existsByJobIdAndSkillId(jobId,skill.getId())){
            throw new IllegalArgumentException("Job already has this skill");
        }

        // 4. Create relationship
        JobSkill jobSkill = new JobSkill();

        jobSkill.setJob(job);
        jobSkill.setSkill(skill);

        jobSkill.setRequired(request.getRequired());

        // 5. Save
        JobSkill savedJobSkill = jobSkillRepository.save(jobSkill);

        // 6. Return response
        return mapToResponse(savedJobSkill);
    }
    private JobSkillResponse mapToResponse(JobSkill jobSkill) {
        return new JobSkillResponse(
                jobSkill.getId(),
                jobSkill.getJob().getId(),
                jobSkill.getSkill().getId(),
                jobSkill.getSkill().getName(),
                jobSkill.isRequired()
        );
    }
}

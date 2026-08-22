package com.hirehub.user.service;

import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.skill.entity.Skill;
import com.hirehub.skill.repository.SkillRepository;
import com.hirehub.user.dto.AddCandidateSkillRequest;
import com.hirehub.user.dto.CandidateSkillResponse;
import com.hirehub.user.entity.CandidateProfile;
import com.hirehub.user.entity.CandidateSkill;
import com.hirehub.user.repository.CandidateProfileRepository;
import com.hirehub.user.repository.CandidateSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateSkillService {

    private final CandidateSkillRepository candidateSkillRepository;

    private final CandidateProfileRepository candidateProfileRepository;

    private final SkillRepository skillRepository;

    public CandidateSkillResponse addSkill(Long candidateId,AddCandidateSkillRequest request){

        // 1. Find candidate
        CandidateProfile candidate = candidateProfileRepository.findById(candidateId)
        .orElseThrow(() -> new ResourceNotFoundException(
                    "Candidate profile not found with id: "
                            + candidateId)
            );

        // 2. Find skill
        Skill skill = skillRepository.findById(request.getSkillId()).orElseThrow(
                () -> new ResourceNotFoundException("Skill not found with id: "+ request.getSkillId())
        );

        // 3. Prevent duplicate skill for the same candidate
        if (candidateSkillRepository
                .existsByCandidateIdAndSkillId(
                        candidateId,
                        request.getSkillId()
                )) {

            throw new IllegalArgumentException(
                    "Candidate already has this skill"
            );
        }

        // 4. Create relationship
        CandidateSkill candidateSkill = new CandidateSkill();

        candidateSkill.setCandidate(candidate);
        candidateSkill.setSkill(skill);

        candidateSkill.setYearsOfExperience(request.getYearsOfExperience());
        candidateSkill.setProficiency(request.getProficiency());

        // 5. Save
        CandidateSkill savedCandidateSkill = candidateSkillRepository.save(candidateSkill);

        // 6. Return response
        return mapToResponse(savedCandidateSkill);
    }
    private CandidateSkillResponse mapToResponse(
            CandidateSkill candidateSkill
    ) {

        return new CandidateSkillResponse(
                candidateSkill.getId(),

                candidateSkill.getCandidate().getId(),

                candidateSkill.getSkill().getId(),
                candidateSkill.getSkill().getName(),

                candidateSkill.getYearsOfExperience(),

                candidateSkill.getProficiency()
        );
    }
}

package com.hirehub.user.controller;

import com.hirehub.user.dto.AddCandidateSkillRequest;
import com.hirehub.user.dto.CandidateSkillResponse;
import com.hirehub.user.service.CandidateSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateSkillController {

    private final CandidateSkillService candidateSkillService;

    @PostMapping("{candidateId}/skills")
    public ResponseEntity<CandidateSkillResponse> addSkill(
            @PathVariable Long candidateId,
            @Valid @RequestBody AddCandidateSkillRequest request)
    {
        CandidateSkillResponse response =
                candidateSkillService.addSkill(
                        candidateId,
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

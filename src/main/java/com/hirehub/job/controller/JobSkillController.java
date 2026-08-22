package com.hirehub.job.controller;

import com.hirehub.job.dto.AddJobSkillRequest;
import com.hirehub.job.dto.JobSkillResponse;
import com.hirehub.job.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @PostMapping("/{jobId}/skills")
    public ResponseEntity<JobSkillResponse> addSkill(
            @PathVariable Long jobId,
            @Valid @RequestBody AddJobSkillRequest request
    ) {

        JobSkillResponse response = jobSkillService.addSkill(jobId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

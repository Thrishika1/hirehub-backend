package com.hirehub.application.controller;

import com.hirehub.application.dto.ApplicationResponse;
import com.hirehub.application.dto.ApplyJobRequest;
import com.hirehub.application.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs/{jobId}/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> applyForJob(
            @PathVariable Long jobId,
            @Valid @RequestBody ApplyJobRequest request
    ) {

        ApplicationResponse response =
                applicationService.applyForJob(jobId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getJobApplications(@PathVariable Long jobId){

        List<ApplicationResponse> applications = applicationService.getJobApplications(jobId);

        return ResponseEntity.ok(applications);
    }
}
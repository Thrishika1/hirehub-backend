package com.hirehub.job.controller;

import com.hirehub.job.dto.CreateJobRequest;
import com.hirehub.job.dto.JobResponse;
import com.hirehub.job.entity.Job;
import com.hirehub.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody CreateJobRequest request){

        JobResponse response = jobService.createJob(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

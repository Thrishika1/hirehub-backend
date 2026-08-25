package com.hirehub.application.controller;

import com.hirehub.application.dto.ApplicationResponse;
import com.hirehub.application.repository.ApplicationRepository;
import com.hirehub.application.service.ApplicationService;
import com.hirehub.user.dto.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/candidates/{candidateId}/applications")
@RequiredArgsConstructor
public class CandidateApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getCandidateApplications(@PathVariable Long candidateId){

        List<ApplicationResponse> applications = applicationService.getCandidateApplications(candidateId);

        return ResponseEntity.ok(applications);
    }

}

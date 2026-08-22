package com.hirehub.user.controller;

import com.hirehub.user.dto.ResumeResponse;
import com.hirehub.user.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates/{candidateId}/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResumeResponse> uploadResume(
            @PathVariable Long candidateId,
            @RequestParam("file")MultipartFile file){

        ResumeResponse response = resumeService.uploadResponse(candidateId,file);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResumeResponse>> getCandidateResumes(
            @PathVariable Long candidateId
    ) {

        List<ResumeResponse> resumes = resumeService.getCandidateResumes(candidateId);

        return ResponseEntity.ok(resumes);
    }

    @PatchMapping("{resumeId}/current")
    public ResponseEntity<ResumeResponse> setCurrentResume(
            @PathVariable Long candidateId, @PathVariable Long resumeId){

        ResumeResponse response = resumeService.setCurrentResume(candidateId, resumeId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(
            @PathVariable Long candidateId,
            @PathVariable Long resumeId
    ) {

        ResumeResponse response = resumeService.getResumeById(candidateId, resumeId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{resumeId}")
    public ResponseEntity<Void> deleteResume(
            @PathVariable Long candidateId,
            @PathVariable Long resumeId
    ){

        resumeService.deleteResume(candidateId, resumeId);

        return ResponseEntity.noContent().build();
    }
}

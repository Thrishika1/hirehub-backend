package com.hirehub.user.controller;

import com.hirehub.user.dto.CandidateProfileResponse;
import com.hirehub.user.dto.CreateCandidateProfileRequest;
import com.hirehub.user.service.CandidateProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<CandidateProfileResponse> createProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateCandidateProfileRequest request){

        CandidateProfileResponse response =
                candidateProfileService.createProfile(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

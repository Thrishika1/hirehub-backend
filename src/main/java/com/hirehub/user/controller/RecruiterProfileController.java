package com.hirehub.user.controller;

import com.hirehub.user.dto.CreateRecruiterProfileRequest;
import com.hirehub.user.dto.RecruiterProfileResponse;
import com.hirehub.user.service.RecruiterProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recruiters")
@RequiredArgsConstructor
public class RecruiterProfileController {

    private final RecruiterProfileService recruiterProfileService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<RecruiterProfileResponse> createProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateRecruiterProfileRequest request){

        RecruiterProfileResponse response = recruiterProfileService.createProfile(userId,request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

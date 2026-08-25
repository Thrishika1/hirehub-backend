package com.hirehub.application.dto;

import com.hirehub.common.enums.ApplicationStatus;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApplicationResponse {

    private Long id;

    private Long candidateId;

    private Long jobId;

    private Long resumeId;

    private ApplicationStatus status;

    private String coverLetter;

    private LocalDateTime appliedAt;
}
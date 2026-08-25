package com.hirehub.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyJobRequest {

    @NotNull(message = "Candidate ID is required")
    private Long candidateId;

    @NotNull(message = "Resume ID is required")
    private Long resumeId;

    @Size(max = 2000, message = "Cover letter cannot exceed 2000 characters")
    private String coverLetter;
}
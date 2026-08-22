package com.hirehub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResumeResponse {

    private Long id;

    private Long candidateId;

    private String fileName;

    //For now, filePath is okay to return because we're developing locally. Later,
    //with S3/cloud storage, we may return a controlled download URL instead of exposing the raw storage key.
    private String filePath;

    private Long fileSize;

    private boolean current;
}

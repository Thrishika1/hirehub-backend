package com.hirehub.application.service;

import com.hirehub.application.dto.ApplicationResponse;
import com.hirehub.application.dto.ApplyJobRequest;
import com.hirehub.application.entity.Application;
import com.hirehub.application.repository.ApplicationRepository;
import com.hirehub.common.enums.ApplicationStatus;
import com.hirehub.common.enums.JobStatus;
import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.job.entity.Job;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.user.dto.ResumeResponse;
import com.hirehub.user.entity.CandidateProfile;
import com.hirehub.user.entity.Resume;
import com.hirehub.user.repository.CandidateProfileRepository;
import com.hirehub.user.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final CandidateProfileRepository candidateProfileRepository;

    private final JobRepository jobRepository;

    private final ResumeRepository resumeRepository;

    public ApplicationResponse applyForJob(Long jobId, ApplyJobRequest request){

        // 1. Find candidate
        CandidateProfile candidate = candidateProfileRepository
                .findById(request.getCandidateId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: "
                                        + request.getCandidateId()
                        )
                );
        // 2. Find job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );

        // 3. Check whether job is published/open
        if (job.getStatus() != JobStatus.OPEN) {
            throw new IllegalStateException(
                    "You cannot apply for this job"
            );
        }

        // 4. Find resume and verify it belongs to candidate
        Resume resume = resumeRepository
                .findByIdAndCandidateId(
                        request.getResumeId(),
                        request.getCandidateId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Resume not found or does not belong to candidate"
                        )
                );

        // 5. Prevent duplicate application
        boolean alreadyApplied =
                applicationRepository
                        .existsByCandidateIdAndJobId(
                                candidate.getId(),
                                job.getId()
                        );

        if (alreadyApplied) {
            throw new IllegalStateException(
                    "You have already applied for this job"
            );
        }

        // 6. Create application
        Application application = new Application();

        application.setCandidate(candidate);
        application.setJob(job);
        application.setResume(resume);
        application.setCoverLetter(request.getCoverLetter());

        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());

        // 7. Save
        Application savedApplication =
                applicationRepository.save(application);

        // 8. Return response
        return mapToResponse(savedApplication);
    }

    public List<ApplicationResponse> getCandidateApplications(Long candidateId){

        // Check whether candidate exists
        candidateProfileRepository.findById(candidateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: " + candidateId
                        )
                );
        // Get all applications
        List<Application> applications =
                applicationRepository.findByCandidateId(candidateId);

        // Convert Application -> ApplicationResponse
        List<ApplicationResponse> response = new ArrayList<>();

        for(Application a : applications){
            response.add(mapToResponse(a));
        }

        return response;
    }

    private ApplicationResponse mapToResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getCandidate().getId(),
                application.getJob().getId(),
                application.getResume().getId(),
                application.getStatus(),
                application.getCoverLetter(),
                application.getAppliedAt()
        );
    }
}

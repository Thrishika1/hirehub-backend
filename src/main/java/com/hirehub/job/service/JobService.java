package com.hirehub.job.service;

import com.hirehub.common.enums.JobStatus;
import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.job.dto.CreateJobRequest;
import com.hirehub.job.dto.JobResponse;
import com.hirehub.job.entity.Job;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.user.entity.RecruiterProfile;
import com.hirehub.user.repository.RecruiterProfileRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    private final RecruiterProfileRepository recruiterProfileRepository;

    public JobResponse createJob(CreateJobRequest request){

        // 1. find recruiter
        RecruiterProfile recruiter = recruiterProfileRepository.findById(request.getRecruiterId())
            .orElseThrow(()->
                new ResourceNotFoundException("\"Recruiter profile not found with id: " +
                                                             + request.getRecruiterId()
            )
        );

        // 2. validate experience range
        if (request.getExperienceMin() != null
                && request.getExperienceMax() != null
                && request.getExperienceMin() > request.getExperienceMax()) {

            throw new IllegalArgumentException(
                    "Minimum experience cannot be greater than maximum experience"
            );
        }

        // 3. Validate salary range
        if (request.getSalaryMin() != null
                && request.getSalaryMax() != null
                && request.getSalaryMin() > request.getSalaryMax()) {

            throw new IllegalArgumentException(
                    "Minimum salary cannot be greater than maximum salary"
            );
        }

        Job job = new Job();

        // Company comes from the recruiter
        job.setCompany(recruiter.getCompany());

        // Recruiter who posted the job
        job.setPostedBy(recruiter);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setEmploymentType(request.getEmploymentType());

        job.setExperienceMin(request.getExperienceMin());
        job.setExperienceMax(request.getExperienceMax());

        job.setSalaryMin(request.getSalaryMin());
        job.setSalaryMax(request.getSalaryMax());

        job.setLocation(request.getLocation());
        job.setWorkMode(request.getWorkMode());

        job.setVacancies(request.getVacancies());

        job.setApplicationDeadline(
                request.getApplicationDeadline()
        );

        /*
         * We do NOT set status here.
         *
         * Entity default:
         *
         * private JobStatus status = JobStatus.DRAFT;
         */

        // 5. Save
        Job savedJob = jobRepository.save(job);

        // 6. Convert Entity -> Response DTO
        return mapToResponse(savedJob);
    }

    public JobResponse openJob(Long jobId){

        //check if job exists
        Job job = jobRepository.findById(jobId).
            orElseThrow(()->
                new ResourceNotFoundException("Job not found with id: "+jobId)
        );

        //check if is already open
        if(job.getStatus() == JobStatus.OPEN){
            throw new IllegalStateException("Job is already open");
        }

        //change status
        job.setStatus(JobStatus.OPEN);

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }

    public JobResponse closeJob(Long jobId){

        //check if job exists
        Job job = jobRepository.findById(jobId).
                orElseThrow(()->
                        new ResourceNotFoundException("Job not found with id: "+jobId)
                );

        //check if is already closed
        if(job.getStatus() == JobStatus.CLOSED){
            throw new IllegalStateException("Job is already closed");
        }

        //change status to closed
        job.setStatus(JobStatus.CLOSED);

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }

    private JobResponse mapToResponse(Job job) {

        return new JobResponse(
                job.getId(),

                job.getCompany().getId(),
                job.getCompany().getName(),

                job.getPostedBy().getId(),

                job.getTitle(),
                job.getDescription(),

                job.getEmploymentType(),

                job.getExperienceMin(),
                job.getExperienceMax(),

                job.getSalaryMin(),
                job.getSalaryMax(),

                job.getLocation(),

                job.getWorkMode(),

                job.getVacancies(),

                job.getStatus(),

                job.getApplicationDeadline()
        );
    }
}

package com.hirehub.user.service;

import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.storage.FileStorageService;
import com.hirehub.user.dto.ResumeResponse;
import com.hirehub.user.entity.CandidateProfile;
import com.hirehub.user.entity.Resume;
import com.hirehub.user.repository.CandidateProfileRepository;
import com.hirehub.user.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
        "application/pdf",
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    private final CandidateProfileRepository candidateProfileRepository;

    private final FileStorageService fileStorageService;

    private final ResumeRepository resumeRepository;

    public List<ResumeResponse> getCandidateResumes(Long candidateId) {

        // Verify candidate exists
        candidateProfileRepository.findById(candidateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: " + candidateId
                        )
                );

        List<Resume> resumes = resumeRepository.findByCandidateId(candidateId);

//        return resumes.stream()
//                .map(this::mapToResponse)
//                .toList();
        List<ResumeResponse> responseList = new ArrayList<>();

        for (Resume resume : resumes) {

            ResumeResponse response =
                    mapToResponse(resume);

            responseList.add(response);
        }

        return responseList;
    }

    public ResumeResponse uploadResponse(Long candidateId, MultipartFile file){

        // 1. Validate candidate
        CandidateProfile candidate = candidateProfileRepository.findById(candidateId)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Candidate not found with id: " + candidateId
                )
        );

        // 2. Validate file
        validateFile(file);

        // 3. Store file
        String filePath = fileStorageService.store(file,"resumes/candidate-"+candidateId);

        // 4. Create resume metadata
        Resume resume = new Resume();

        resume.setCandidate(candidate);
        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath);
        resume.setFileSize(file.getSize());

        /*
         * For now, if this is the first resume,
         * it becomes the current resume.
         */
        boolean hasExistingResume = !resumeRepository.findByCandidateId(candidateId).isEmpty();
        resume.setCurrent(!hasExistingResume);

        Resume savedResume = resumeRepository.save(resume);

        return mapToResponse(savedResume);
    }

    private void validateFile(MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Resume file cannot be empty");
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Only PDF, DOC and DOCX files are allowed");
        }

        // 5 MB
        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("Resume file size must not exceed 5 MB");
        }
    }

    private ResumeResponse mapToResponse(Resume resume) {

        return new ResumeResponse(
                resume.getId(),
                resume.getCandidate().getId(),
                resume.getFileName(),
                resume.getFilePath(),
                resume.getFileSize(),
                resume.isCurrent()
        );
    }

    public ResumeResponse setCurrentResume(Long candidateId,Long resumeId){

        // 1. Find the requested resume and verify ownership
        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId,candidateId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Resume not found with id: " + resumeId
                )
        );

        // 2. Find the currently active resume
        Optional<Resume> currentResume = resumeRepository.findByCandidateIdAndCurrentTrue(candidateId);

        // If an old current resume exists, make it false
        if (currentResume.isPresent()) {

            Resume oldResume = currentResume.get();

            oldResume.setCurrent(false);

            //save to db
            resumeRepository.save(oldResume);
        }

        // Make the selected resume current
        resume.setCurrent(true);

        // save updated resume, /current resume
        Resume updatedResume = resumeRepository.save(resume);

        return mapToResponse(updatedResume);
    }

    public ResumeResponse getResumeById(Long candidateId, Long resumeId) {

        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId, candidateId)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Resume not found with id: " + resumeId
                )
            );

        return mapToResponse(resume);
    }

    public void deleteResume(Long candidateId,Long resumeId){
        // Find resume and verify it belongs to the candidate
        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId, candidateId)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Resume not found with id: " + resumeId
                )
        );

        // Delete actual file
        fileStorageService.delete(resume.getFilePath());

        // Delete database record
        resumeRepository.delete(resume);
    }
}

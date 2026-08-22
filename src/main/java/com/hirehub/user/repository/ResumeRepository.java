package com.hirehub.user.repository;

import com.hirehub.user.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume,Long> {

    List<Resume> findByCandidateId(Long candidateId);

    boolean existsByIdAndCandidateId(Long resumeId, Long candidateId);

    Optional<Resume> findByIdAndCandidateId(Long resumeId, Long candidateId);

    Optional<Resume> findByCandidateIdAndCurrentTrue(Long candidateId);
}

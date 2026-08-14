package com.hirehub.user.repository;

import com.hirehub.user.entity.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile,Long> {

    Optional<CandidateProfile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}

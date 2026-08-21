package com.hirehub.user.repository;

import com.hirehub.user.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterProfileRepository
        extends JpaRepository<RecruiterProfile, Long> {

    boolean existsByUserId(Long userId);
}
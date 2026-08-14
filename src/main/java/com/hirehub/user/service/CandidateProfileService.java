package com.hirehub.user.service;

import com.hirehub.common.enums.Role;
import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.user.dto.CandidateProfileResponse;
import com.hirehub.user.dto.CreateCandidateProfileRequest;
import com.hirehub.user.entity.CandidateProfile;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.CandidateProfileRepository;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {

    private final UserRepository userRepository;
    private final CandidateProfileRepository candidateProfileRepository;

    public CandidateProfileResponse createProfile(
            Long userId,
            CreateCandidateProfileRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        )
                );

        if (user.getRole() != Role.CANDIDATE) {
            throw new IllegalArgumentException(
                    "Only users with CANDIDATE role can create a candidate profile"
            );
        }

        if (candidateProfileRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException(
                    "Candidate profile already exists for user: " + userId
            );
        }

        CandidateProfile profile = new CandidateProfile();

        profile.setUser(user);
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhone(request.getPhone());
        profile.setHeadline(request.getHeadline());
        profile.setAbout(request.getAbout());
        profile.setCity(request.getCity());
        profile.setState(request.getState());
        profile.setCountry(request.getCountry());
        profile.setProfilePictureUrl(request.getProfilePictureUrl());
        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setGithubUrl(request.getGithubUrl());
        profile.setPortfolioUrl(request.getPortfolioUrl());

        CandidateProfile savedProfile = candidateProfileRepository.save(profile);

        return new CandidateProfileResponse(
                savedProfile.getId(),
                savedProfile.getUser().getId(),
                savedProfile.getFirstName(),
                savedProfile.getLastName(),
                savedProfile.getPhone(),
                savedProfile.getHeadline(),
                savedProfile.getAbout(),
                savedProfile.getCity(),
                savedProfile.getState(),
                savedProfile.getCountry(),
                savedProfile.getProfilePictureUrl(),
                savedProfile.getLinkedinUrl(),
                savedProfile.getGithubUrl(),
                savedProfile.getPortfolioUrl()
        );
    }
}

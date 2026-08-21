package com.hirehub.user.service;

import com.hirehub.common.enums.Role;
import com.hirehub.common.exception.ResourceNotFoundException;
import com.hirehub.company.entity.Company;
import com.hirehub.company.repository.CompanyRepository;
import com.hirehub.user.dto.CreateRecruiterProfileRequest;
import com.hirehub.user.dto.RecruiterProfileResponse;
import com.hirehub.user.entity.RecruiterProfile;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.RecruiterProfileRepository;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    public RecruiterProfileResponse createProfile(
            Long userId, CreateRecruiterProfileRequest request){

        //1. Find User
        User user = userRepository.findById(userId)
            .orElseThrow(() ->
                new ResourceNotFoundException("User not found with id "+userId)
        );
        //2. Validate role
        if(user.getRole()!= Role.RECRUITER){
            throw new IllegalArgumentException(
                "Only users with RECRUITER role can create a recruiter profile");
        }
        // 3. check duplicate profile
        if(recruiterProfileRepository.existsByUserId(userId)){
            throw new IllegalArgumentException(
                "Recruiter profile already exists for user: " + userId
            );
        }

        // 4. find company
        Company company = companyRepository.findById(request.getCompanyId())
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Company not found with id: "
                            + request.getCompanyId()
                )
        );

        // 5. create profile
        RecruiterProfile profile = new RecruiterProfile();

        profile.setUser(user);
        profile.setCompany(company);

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhone(request.getPhone());
        profile.setDesignation(request.getDesignation());

        // 6. Save
        RecruiterProfile savedProfile =
                recruiterProfileRepository.save(profile);

        // 7. Return response
        return new RecruiterProfileResponse(
                savedProfile.getId(),
                savedProfile.getUser().getId(),

                savedProfile.getCompany().getId(),
                savedProfile.getCompany().getName(),

                savedProfile.getFirstName(),
                savedProfile.getLastName(),
                savedProfile.getPhone(),
                savedProfile.getDesignation()
        );
    }
}

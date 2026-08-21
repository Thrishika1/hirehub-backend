package com.hirehub.company.service;

import com.hirehub.company.dto.CompanyResponse;
import com.hirehub.company.dto.CreateCompanyRequest;
import com.hirehub.company.entity.Company;
import com.hirehub.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyResponse createCompany(CreateCompanyRequest request){

        //check if company already exists
        if(companyRepository.existsByName(request.getName())){
            throw new IllegalArgumentException("Company already exists with name "+request.getName());
        }

        // 2. Create Company entity
        Company company = new Company();

        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setIndustry(request.getIndustry());
        company.setCompanySize(request.getCompanySize());
        company.setFoundedYear(request.getFoundedYear());
        company.setCity(request.getCity());
        company.setState(request.getState());
        company.setCountry(request.getCountry());
        company.setLogoUrl(request.getLogoUrl());

        // verified remains false by default
        // company.setVerified(false); // not necessary

        // 3. Save
        Company savedCompany = companyRepository.save(company);

        // 4. Convert Entity -> Response DTO
        return new CompanyResponse(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getDescription(),
                savedCompany.getWebsite(),
                savedCompany.getEmail(),
                savedCompany.getPhone(),
                savedCompany.getIndustry(),
                savedCompany.getCompanySize(),
                savedCompany.getFoundedYear(),
                savedCompany.getCity(),
                savedCompany.getState(),
                savedCompany.getCountry(),
                savedCompany.getLogoUrl(),
                savedCompany.isVerified()
        );
    }
}

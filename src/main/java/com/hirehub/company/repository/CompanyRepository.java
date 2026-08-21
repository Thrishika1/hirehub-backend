package com.hirehub.company.repository;

import com.hirehub.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    boolean existsByName(String name);
}

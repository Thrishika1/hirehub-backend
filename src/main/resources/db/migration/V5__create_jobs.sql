CREATE TABLE jobs (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    company_id BIGINT NOT NULL,
    posted_by BIGINT NOT NULL,

    title VARCHAR(200) NOT NULL,
    description VARCHAR(5000) NOT NULL,
    employment_type VARCHAR(20) NOT NULL,

    experience_min INT,
    experience_max INT,

    salary_min BIGINT,
    salary_max BIGINT,

    location VARCHAR(150) NOT NULL,
    work_mode VARCHAR(20) NOT NULL,

    vacancies INT NOT NULL DEFAULT 1,
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',

    application_deadline DATETIME,

    CONSTRAINT pk_jobs PRIMARY KEY (id),

    CONSTRAINT fk_jobs_company
        FOREIGN KEY (company_id)
        REFERENCES companies(id),

    CONSTRAINT fk_jobs_recruiter
        FOREIGN KEY (posted_by)
        REFERENCES recruiter_profiles(id)
);
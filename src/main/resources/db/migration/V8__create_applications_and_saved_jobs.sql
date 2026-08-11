CREATE TABLE applications (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    candidate_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    resume_id BIGINT NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'APPLIED',
    cover_letter VARCHAR(2000),
    applied_at DATETIME NOT NULL,

    CONSTRAINT pk_applications PRIMARY KEY (id),

    CONSTRAINT uk_application_candidate_job
        UNIQUE (candidate_id, job_id),

    CONSTRAINT fk_applications_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate_profiles(id),

    CONSTRAINT fk_applications_job
        FOREIGN KEY (job_id)
        REFERENCES jobs(id),

    CONSTRAINT fk_applications_resume
        FOREIGN KEY (resume_id)
        REFERENCES resumes(id)
);


CREATE TABLE saved_jobs (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    candidate_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    saved_at DATETIME NOT NULL,

    CONSTRAINT pk_saved_jobs PRIMARY KEY (id),

    CONSTRAINT uk_saved_job_candidate_job
        UNIQUE (candidate_id, job_id),

    CONSTRAINT fk_saved_jobs_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate_profiles(id),

    CONSTRAINT fk_saved_jobs_job
        FOREIGN KEY (job_id)
        REFERENCES jobs(id)
);
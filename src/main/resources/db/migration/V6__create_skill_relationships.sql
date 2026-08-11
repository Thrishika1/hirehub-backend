CREATE TABLE candidate_skills (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    candidate_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,

    years_of_experience INT,
    proficiency VARCHAR(20),

    CONSTRAINT pk_candidate_skills PRIMARY KEY (id),

    CONSTRAINT uk_candidate_skill
        UNIQUE (candidate_id, skill_id),

    CONSTRAINT fk_candidate_skills_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate_profiles(id),

    CONSTRAINT fk_candidate_skills_skill
        FOREIGN KEY (skill_id)
        REFERENCES skills(id)
);

CREATE TABLE job_skills (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    job_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,

    required BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_job_skills PRIMARY KEY (id),

    CONSTRAINT uk_job_skill
        UNIQUE (job_id, skill_id),

    CONSTRAINT fk_job_skills_job
        FOREIGN KEY (job_id)
        REFERENCES jobs(id),

    CONSTRAINT fk_job_skills_skill
        FOREIGN KEY (skill_id)
        REFERENCES skills(id)
);
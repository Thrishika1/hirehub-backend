CREATE TABLE educations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    candidate_id BIGINT NOT NULL,
    institution VARCHAR(200) NOT NULL,
    degree VARCHAR(100) NOT NULL,
    field_of_study VARCHAR(150),
    cgpa DOUBLE,
    start_year INT,
    end_year INT,

    CONSTRAINT pk_educations PRIMARY KEY (id),

    CONSTRAINT fk_educations_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate_profiles(id)
);


CREATE TABLE experiences (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    candidate_id BIGINT NOT NULL,
    company_name VARCHAR(200) NOT NULL,
    designation VARCHAR(150) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    currently_working BOOLEAN NOT NULL DEFAULT FALSE,
    description VARCHAR(2000),

    CONSTRAINT pk_experiences PRIMARY KEY (id),

    CONSTRAINT fk_experiences_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate_profiles(id)
);


CREATE TABLE resumes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    candidate_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT NOT NULL,
    current BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_resumes PRIMARY KEY (id),

    CONSTRAINT fk_resumes_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate_profiles(id)
);
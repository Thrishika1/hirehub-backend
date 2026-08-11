CREATE TABLE candidate_profiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    user_id BIGINT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone VARCHAR(20),
    headline VARCHAR(150),
    about VARCHAR(2000),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    profile_picture_url VARCHAR(500),
    linkedin_url VARCHAR(500),
    github_url VARCHAR(500),
    portfolio_url VARCHAR(500),

    CONSTRAINT pk_candidate_profiles PRIMARY KEY (id),
    CONSTRAINT uk_candidate_profiles_user UNIQUE (user_id),

    CONSTRAINT fk_candidate_profiles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE recruiter_profiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    user_id BIGINT NOT NULL,
    company_id BIGINT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone VARCHAR(20),
    designation VARCHAR(100),

    CONSTRAINT pk_recruiter_profiles PRIMARY KEY (id),
    CONSTRAINT uk_recruiter_profiles_user UNIQUE (user_id),

    CONSTRAINT fk_recruiter_profiles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);
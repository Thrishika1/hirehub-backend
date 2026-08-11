CREATE TABLE companies (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    name VARCHAR(200) NOT NULL,
    description VARCHAR(2000),
    website VARCHAR(500),
    email VARCHAR(255),
    phone VARCHAR(20),
    industry VARCHAR(100),
    company_size VARCHAR(50),
    founded_year INT,
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    logo_url VARCHAR(500),
    verified BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_companies PRIMARY KEY (id),
    CONSTRAINT uk_companies_name UNIQUE (name)
);

ALTER TABLE recruiter_profiles
ADD CONSTRAINT fk_recruiter_profiles_company
    FOREIGN KEY (company_id)
    REFERENCES companies(id);
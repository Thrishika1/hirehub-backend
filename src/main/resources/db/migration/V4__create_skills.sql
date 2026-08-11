CREATE TABLE skills (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    name VARCHAR(100) NOT NULL,

    CONSTRAINT pk_skills PRIMARY KEY (id),
    CONSTRAINT uk_skills_name UNIQUE (name)
);
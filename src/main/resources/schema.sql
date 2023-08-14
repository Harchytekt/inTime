-- DB Creation

-- DROP DATABASE IF EXISTS in_time;
-- CREATE DATABASE IF NOT EXISTS in_time;

DROP TABLE IF EXISTS time_entries;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS workspaces;

CREATE TABLE IF NOT EXISTS workspaces (
    id INT NOT NULL AUTO_INCREMENT,
    toggl_id INT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name, toggl_id)
);

CREATE TABLE IF NOT EXISTS clients (
    id INT NOT NULL AUTO_INCREMENT,
    toggl_id INT,
    name VARCHAR(50) NOT NULL,
    fk_workspace INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_workspace) REFERENCES workspaces(id),
    UNIQUE (name, toggl_id)
);

CREATE TABLE IF NOT EXISTS projects (
    id INT NOT NULL AUTO_INCREMENT,
    toggl_id INT,
    name VARCHAR(50) NOT NULL,
    billable BOOLEAN NOT NULL DEFAULT FALSE,
    fk_client INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_client) REFERENCES clients(id),
    UNIQUE (name, toggl_id)
);

CREATE TABLE IF NOT EXISTS time_entries (
    id INT NOT NULL AUTO_INCREMENT,
    toggl_id INT,
    start_date TIMESTAMP NOT NULL DEFAULT NOW(),
    end_date TIMESTAMP,
    duration INT,
    description VARCHAR(256),
    running BOOLEAN NOT NULL DEFAULT TRUE,
    fk_project INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_project) REFERENCES projects(id),
    UNIQUE (toggl_id)
);
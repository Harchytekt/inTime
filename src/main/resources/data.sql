-- DB Creation

-- DROP DATABASE IF EXISTS in_time;
-- CREATE DATABASE IF NOT EXISTS in_time;

CREATE TABLE IF NOT EXISTS workspaces (
    id INT NOT NULL AUTO_INCREMENT,
    togglId INT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS clients (
    id INT NOT NULL AUTO_INCREMENT,
    togglId INT,
    name VARCHAR(50) NOT NULL,
    fk_workspace INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_workspace) REFERENCES workspaces(id)
);

CREATE TABLE IF NOT EXISTS projects (
    id INT NOT NULL AUTO_INCREMENT,
    togglId INT,
    name VARCHAR(50) NOT NULL,
    billable BOOLEAN NOT NULL DEFAULT FALSE,
    fk_workspace INT NOT NULL,
    fk_client INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_workspace) REFERENCES workspaces(id),
    FOREIGN KEY (fk_client) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS time_entries (
    id INT NOT NULL AUTO_INCREMENT,
    togglId INT,
    startDate TIMESTAMP NOT NULL DEFAULT NOW(),
    endDate TIMESTAMP,
    duration INT NOT NULL,
    description VARCHAR(256),
    running BOOLEAN NOT NULL DEFAULT TRUE,
    fk_project INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_project) REFERENCES projects(id)
);

-- Data Insertion

INSERT INTO workspaces (id, name)
VALUES (1, 'Test');

INSERT INTO clients (id, name, fk_workspace)
VALUES (1, 'First Client', 1),
    (2, 'Second Client', 1);

INSERT INTO projects (id, name, fk_client, fk_workspace)
VALUES (1, 'First Project', 1, 1),
    (2, 'Second Project', 2, 1);

INSERT INTO time_entries (id, duration, fk_project)
VALUES (1, 1, 1);

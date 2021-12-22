-- DB Creation

-- DROP DATABASE IF EXISTS in_time;
-- CREATE DATABASE IF NOT EXISTS in_time;

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

-- Data Insertion

INSERT INTO workspaces (id, name)
VALUES (1, 'My First Workspace'),
    (2, 'My Second Workspace');

INSERT INTO clients (id, name, fk_workspace)
VALUES (1, 'My First Client', 1),
    (2, 'My Second Client', 1);

INSERT INTO projects (id, name, billable, fk_client)
VALUES (1, 'My First Project', false, 1),
    (2, 'My Second Project', false, 1);

INSERT INTO time_entries (id, start_date, end_date, duration, running, fk_project)
VALUES (1, '2021-01-01T14:28:42', '2021-01-01T14:29:08', 26 , false, 1);

INSERT INTO time_entries (id, start_date, fk_project)
VALUES (2, '2021-01-01T14:29:08', 1);
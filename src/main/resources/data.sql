-- DB Creation

CREATE DATABASE IF NOT EXISTS database_name;

USE database_name;

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

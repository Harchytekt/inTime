-- Data Insertion

INSERT INTO workspaces (id, name)
VALUES (1, 'Test');

INSERT INTO clients (id, name, fk_workspace)
VALUES (1, 'First Client', 1),
    (2, 'Second Client', 1);

INSERT INTO projects (id, name, billable, fk_client, fk_workspace)
VALUES (1, 'First Project', false, 1, 1),
    (2, 'Second Project', false, 2, 1);

INSERT INTO time_entries (id, fk_project)
VALUES (1, 1);

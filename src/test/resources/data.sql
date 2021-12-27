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
# Get All Workspaces

> Last modified: 21/02/2021 (v0.0.1)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Get a list of all workspaces from the server.

**Auth required:** No  
**Permissions required:** None

**Enpoint's URL:** `GET /workspace`

## Response parameters

For the description of the workspaces, see [Get Workspace by ID](Get-Workspace-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/workspace
```

**Code:** `200 CREATED`

**Content:**

```json
[
  {
    "id": 1,
    "togglId": null,
    "name": "My workspace"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "My second workspace"
  }
]
```

### Failure

#### No Workspace Found

```shell
curl "http://localhost:8080/workspace
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-02-21T09:41:00.000+0000",
  "status": 404,
  "message": "No entity 'Workspace' found!",
  "path": "/workspace"
}
```
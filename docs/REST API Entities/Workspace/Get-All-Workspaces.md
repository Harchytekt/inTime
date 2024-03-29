# Get All Workspaces

> Last modified: 2023-08-14 (v0.0.6)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Get a list of all Workspaces from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /workspace`

## Response parameters

For the description of the Workspace entity, see [Get Workspace by ID](Get-Workspace-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/workspace"
```

**Code:** `200 SUCCESS`

**Content:**

```json
[
  {
    "id": 1,
    "name": "My First Workspace"
  },
  {
    "id": 2,
    "name": "My Second Workspace"
  }
]
```

### Failure

#### No Workspace Found

```shell
curl "http://localhost:8080/workspace"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No entity 'Workspace' found!",
  "path": "/workspace"
}
```

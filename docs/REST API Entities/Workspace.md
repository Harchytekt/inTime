# Workspace

Back to the [menu](../README.md)

- [Get All Workspaces (`GET /workspace`) 🔗](Workspace/Get-All-Workspaces.md)
- [Get Workspace by ID (`GET /workspace/{id}`) 🔗](Workspace/Get-Workspace-by-ID.md)
- [Get Clients by Workspace ID (`GET /workspace/{id}/clients`) 🔗](Workspace/Get-Workspace-by-ID.md)
- [Create a New Workspace (`POST /workspace/{id}`) 🔗](Workspace/Create-New-Workspace.md)
- [Update Workspace by ID (`PUT /workspace/{id}`) 🔗](Workspace/Update-Workspace-by-ID.md)
- `DELETE`  `/workspace/{id}` [🔗](#delete-workspace-by-id)
- `DELETE`  `/workspace/{id}/force` [🔗](#force-delete-workspace-by-id)

## Delete Workspace by ID

### URL STRUCTURE

`http://localhost:8080/workspace/{id}`

### METHOD

`DELETE`

### EXAMPLE

```curl
curl -X "DELETE" "http://localhost:8080/workspace/1"
```

### RETURNS

> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the name (`name`).
> ⚠️ If the Workspace has still children, you won't be able to delete it.  
> If you still want to delete this Workspace, you may use a _[force delete](https://github.com/Harchytekt/inTime/blob/master/docs/REST%20API%20Entities/Workspace.md#force-delete-time-entry-by-id)_.

#### Sample Response

```json
{
  "id": 1,
  "togglId": null,
  "name": "My workspace"
}
```

## Force Delete Workspace by ID

### URL STRUCTURE

`http://localhost:8080/workspace/{id}/force`

### METHOD

`DELETE`

### EXAMPLE

```curl
curl -X "DELETE" "http://localhost:8080/workspace/1/force"
```

### RETURNS

> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the name (`name`).
> ⚠️ If the Workspace has still children, you won't be able to delete it.

#### Sample Response

```json
{
  "id": 1,
  "togglId": null,
  "name": "My workspace"
}
```

# Workspace

Back to the [menu](../README.md)

## Summary

- `GET`  `/workspace` [🔗](#get-all-workspaces)
- `GET`  `/workspace/{id}` [🔗](#get-workspace-by-id)
- `GET`  `/workspace/{id}/clients` [🔗](#get-clients-by-workspace-id)
- `POST` `/workspace/{id}` + body [🔗](#create-a-new-workspace)
- `PUT`  `/workspace/{id}` + body [🔗](#update-workspace)
- `DELETE`  `/workspace/{id}` [🔗](#delete-workspace-by-id)
- `DELETE`  `/workspace/{id}/force` [🔗](#force-delete-workspace-by-id)

## Get All Workspaces

### URL STRUCTURE

`http://localhost:8080/workspace`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/workspace"
```

### RETURNS

> HTTP Status code: `200`

A list of JSON-encoded dictionaries including an ID (`id`), Toggl ID (`togglId`) and the name (`name`).

#### Sample response

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

## Get Workspace by ID

### URL STRUCTURE

`http://localhost:8080/workspace/{id}`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/workspace/1"
```

### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the name (`name`).

#### Sample response

```json
{
  "id": 1,
  "togglId": null,
  "name": "My workspace"
}
```

## Get Clients by Workspace ID

### URL STRUCTURE

`http://localhost:8080/workspace/{id}/clients`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/workspace/1/clients"
```

### RETURNS

> HTTP Status code: `200`

A list of JSON-encoded dictionaries including an ID (`id`), Toggl ID (`togglId`), name (`name`) and the workspace
name (`workspaceName`).

#### Sample response

```json
[
  {
    "id": 1,
    "togglId": null,
    "name": "First Client",
    "workspaceName": "My workspace"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "Second Client",
    "workspaceName": "My workspace"
  }
]
```

## Create a new Workspace

### URL STRUCTURE

`http://localhost:8080/workspace`

### METHOD

`POST`

#### Parameters

```json
{
    "name": "Workspace name"
}
```

- `name` _**String**_: The name of the Workspace.

### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/workspace" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My second workspace"
}'
```

### RETURNS
> HTTP Status code: `201`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the workspace name (`name`).  
> The Toggl ID is obviously `null` at this point.

#### Sample Response
```json
{
    "id": 2,
    "togglId": null,
    "name": "My second workspace"
}
```

## Update Workspace
### URL STRUCTURE
`http://localhost:8080/workspace/{id}`

### METHOD
`PUT`

#### Parameters
```json
{
    "togglId": 1,
    "name": "Workspace name"
}
```

- `togglId` _**Long?**_: The ID of the Workspace in Toggl.
- `name` _**String?**_: The name of the Workspace.

### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/workspace/2" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 2
}'
```

### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the workspace name (`name`).

#### Sample Response

```json
{
  "id": 2,
  "togglId": 2,
  "name": "My second workspace"
}
```

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

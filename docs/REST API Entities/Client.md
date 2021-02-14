# Client

Back to the [menu](../README.md)

## Summary

- `GET`  `/client` [🔗](#get-all-clients)
- `GET`  `/client/{id}` [🔗](#get-client-by-id)
- `GET`  `/client/{id}/projects` [🔗](#get-projects-by-client-id)
- `POST` `/client/{id}` + body [🔗](#create-a-new-client)
- `PUT`  `/client/{id}` + body [🔗](#update-client)
- `DELETE`  `/client/{id}` [🔗](#delete-client-by-id)
- `DELETE`  `/client/{id}/force` [🔗](#force-delete-client-by-id)

## Get All Clients

### URL STRUCTURE

`http://localhost:8080/client`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/client"
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
    "name": "My workspace"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "My second workspace"
  }
]
```

## Get Client by ID

### URL STRUCTURE

`http://localhost:8080/client/{id}`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/client/1"
```

### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`) and the workspace name (`workspaceName`).

#### Sample response

```json
{
  "id": 1,
  "togglId": null,
  "name": "First Client",
  "workspaceName": "My workspace"
}
```

## Get Projects by Client ID

### URL STRUCTURE

`http://localhost:8080/client/{id}/projects`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/client/1/projects"
```

### RETURNS

> HTTP Status code: `200`

A list of JSON-encoded dictionaries including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable
state (`billable`) and the client name (`clientName`).

#### Sample response

```json
[
  {
    "id": 1,
    "togglId": null,
    "name": "First Project",
    "billable": false,
    "clientName": "First Client"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "Second Project",
    "billable": false,
    "clientName": "First Client"
  }
]
```

## Create a new Client

### URL STRUCTURE

`http://localhost:8080/client`

### METHOD

`POST`

#### Parameters

```json
{
    "name": "Client name",
    "workspaceName": "Workspace name"
}
```

- `name` _**String**_: The name of the Client.
- `workspaceName` _**String**_: The name of the workspace to which the Client belongs.

### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/client" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "Third Client",
  "workspaceName": "My workspace"
}'
```

### RETURNS
> HTTP Status code: `201`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), and the workspace name (`workspaceName`).  
> The Toggl ID is obviously `null` at this point.

#### Sample Response
```json
{
    "id": 3,
    "togglId": null,
    "name": "Third Client",
    "workspaceName": "My workspace"
}
```

## Update Client
### URL STRUCTURE
`http://localhost:8080/client/{id}`

### METHOD
`PUT`

#### Parameters
```json
{
    "togglId": 1,
    "name": "Client name",
    "workspaceName": "Workspace name"
}
```

- `togglId` _**Long?**_: The ID of the Client in Toggl.
- `name` _**String?**_: The name of the Client.
- `workspaceName` _**String?**_:  The name of the workspace to which the Client belongs.

### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/client/3" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 5
}'
```

### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), and the workspace name (`workspaceName`).

#### Sample Response

```json
{
  "id": 3,
  "togglId": 5,
  "name": "Third Client",
  "workspaceName": "My workspace"
}
```

## Delete Client by ID

### URL STRUCTURE

`http://localhost:8080/client/{id}`

### METHOD

`DELETE`

### EXAMPLE

```curl
curl -X "DELETE" "http://localhost:8080/client/1"
```

### RETURNS

> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), and the workspace
name (`workspaceName`).
> ⚠️ If the Client has still children, you won't be able to delete it.  
> If you still want to delete this Client, you may use a _[force delete](https://github.com/Harchytekt/inTime/blob/master/docs/REST%20API%20Entities/Client.md#force-delete-time-entry-by-id)_.

#### Sample Response

```json
{
  "id": 1,
  "togglId": null,
  "name": "First Client",
  "workspaceName": "My workspace"
}
```

## Force Delete Client by ID

### URL STRUCTURE

`http://localhost:8080/client/{id}/force`

### METHOD

`DELETE`

### EXAMPLE

```curl
curl -X "DELETE" "http://localhost:8080/client/1/force"
```

### RETURNS

> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), and the workspace
name (`workspaceName`).
> ⚠️ If the Client has still children, you won't be able to delete it.

#### Sample Response

```json
{
  "id": 1,
  "togglId": null,
  "name": "First Client",
  "workspaceName": "My workspace"
}
```

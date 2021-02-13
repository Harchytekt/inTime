# Client
**TLDR**  
`GET`  /client/{id}  
`POST` /client/{id}  
`PUT`  /client/{id}

**Summary**  
- `GET`  `/client/{id}` [🔗](#get-client-by-id)
- `POST` `/client/{id}` + body [🔗](#create-a-new-client)
- `PUT`  `/client/{id}` + body [🔗](#update-client)

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

# Workspace

### Get Workspace by ID
#### URL STRUCTURE
`http://localhost:8080/workspace/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/workspace/1"
```

#### RETURNS
A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the name (`name`).

##### Sample response
```json
{
    "id": 1,
    "togglId": null,
    "name": "My workspace"
}
```

### Create a new Workspace
#### URL STRUCTURE
`http://localhost:8080/workspace`

#### METHOD
`POST`

##### Parameters
```json
{
    "name": "Workspace name"
}
```

- `name` _**String**_: The name of the Workspace.

#### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/workspace" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My second workspace"
}'
```

#### RETURNS
A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the workspace name (`name`).  
> The Toggl ID is obviously `null` at this point.

##### Sample Response 
```json
{
    "id": 2,
    "togglId": null,
    "name": "My second workspace"
}
```

### Update Workspace
#### URL STRUCTURE
`http://localhost:8080/workspace/{id}`

#### METHOD
`PUT`

##### Parameters
```json
{
    "togglId": 1,
    "name": "Workspace name"
}
```

- `togglId` _**Long?**_: The ID of the Workspace in Toggl.
- `name` _**String?**_: The name of the Workspace.

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/workspace/2" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 2
}'
```

#### RETURNS
A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`) and the workspace name (`name`).

##### Sample Response 
```json
{
    "id": 2,
    "togglId": 2,
    "name": "My second workspace"
}
```

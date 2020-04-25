# Workspace

### Get by id
#### URL STRUCTURE
`http://localhost:8080/workspace/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/workspace/1"
```

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`) and the name (`name`).

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
`http://localhost:8080/workspace/`

#### METHOD
`POST`

#### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/workspace/" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My second workspace"
}'
```

##### Parameters
```json
{
    "name": "My second workspace"
}
```

- `name` _**String**_: The name of the Workspace.

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`) and the workspace name (`name`).  
> The Toggl id is obviously `null` at this point.

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

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/workspace/3" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 2
}'
```

##### Parameters
```json
{
    "togglId": 1,
    "name": "Workspace name"
}
```

- `togglId` _**Long?**_: The id of the Client in Toggl.
- `name` _**String?**_: The name of the Workspace.

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`) and the workspace name (`name`).

##### Sample Response 
```json
{
    "id": 2,
    "togglId": 2,
    "name": "My second workspace"
}
```

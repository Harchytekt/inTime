## Project

### Get by id
#### URL STRUCTURE
`http://localhost:8080/project/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/project/1"
```

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

##### Sample response
```json
{
    "id": 1,
    "togglId": null,
    "name": "First Project",
    "billable": false,
    "clientName": "First Client"
}
```

### Create a new Project
#### URL STRUCTURE
`http://localhost:8080/project/`

#### METHOD
`POST`

#### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/project/"  \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "Third Project",
  "clientName": "First Client"
}'
```

##### Parameters
```json
{
    "name": "Third Project",
    "clientName": "First Client"
}
```

- `name` _**String**_: The name of the project to create.
- `clientName` _**String**_: The name of the client to which the project is associated.

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

###### Sample Response
```json
{
    "id": 3,
    "togglId": null,
    "name": "Third Project",
    "billable": false,
    "clientName": "First Client"
}
```

### Update Project
#### URL STRUCTURE
`http://localhost:8080/project/{id}`

#### METHOD
`PUT`

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/project/3" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "Third Project",
  "togglId": 12,
  "billable": true,
  "clientName": "Second Client"
}'
```

##### Parameters
```json
{
    "togglId": 12,
    "billable": true,
    "clientName": "Second Client"
}
```

- `name` _**String?**_ The name of the project.
- `togglId` _**Long?**_ The id of the Project in Toggl.
- `billable` _**Boolean?**_ The billable state of the project.
- `clientName` _**String?**_ The name of the Client to which the Project belongs.

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

###### Sample Response
```json
{
    "id": 3,
    "togglId": 12,
    "name": "Third Project",
    "billable": true,
    "clientName": "Second Client"
}
```

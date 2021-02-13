# Project

### Get Project by ID
#### URL STRUCTURE
`http://localhost:8080/project/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/project/1"
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

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
`http://localhost:8080/project`

#### METHOD
`POST`

##### Parameters
```json
{
    "name": "Project name",
    "billable": false,
    "clientName": "Client name"
}
```

- `name` _**String**_: The name of the project.
- `billable` _**Boolean?**_: The billable state of the Project. The default for this field is `false`.
- `clientName` _**String**_: The name of the client to which the Project belongs.

#### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/project" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "Third Project",
  "clientName": "First Client"
}'
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).  
> The Toggl ID is obviously `null` at this point.

##### Sample Response
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

##### Parameters
```json
{
    "togglId": 1,
    "name": "Project name",
    "billable": true,
    "clientName": "Client name"
}
```

- `togglId` _**Long?**_: The ID of the Project in Toggl.
- `name` _**String?**_: The name of the Project.
- `billable` _**String?**_: The billable state of the Project. The default for this field is `false`.
- `clientName` _**String?**_:  The name of the client to which the Project belongs.

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/project/3" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 12,
  "billable": true
}'
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

##### Sample Response
```json
{
    "id": 3,
    "togglId": 12,
    "name": "Third Project",
    "billable": true,
    "clientName": "First Client"
}
```

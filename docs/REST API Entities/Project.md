# Project

Back to the [menu](../README.md)

## Summary

- `GET`  `/project` [🔗](#get-all-projects)
- `GET`  `/project/{id}` [🔗](#get-project-by-id)
- `GET`  `/project/{id}/timeentries` [🔗](#get-time-entries-by-project-id)
- `POST` `/project/{id}` + body [🔗](#create-a-new-project)
- `PUT`  `/project/{id}` + body [🔗](#update-project)
- `DELETE`  `/project/{id}` [🔗](#delete-project-by-id)
- `DELETE`  `/project/{id}/force` [🔗](#force-delete-project-by-id)

## Get All Projects

### URL STRUCTURE

`http://localhost:8080/project`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/project"
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

## Get Project by ID

### URL STRUCTURE

`http://localhost:8080/project/{id}`

### METHOD

`GET`

### EXAMPLE

```curl
curl "http://localhost:8080/project/1"
```

### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

#### Sample response
```json
{
    "id": 1,
    "togglId": null,
    "name": "First Project",
    "billable": false,
    "clientName": "First Client"
}
```

## Get Time Entries by Project ID
### URL STRUCTURE

`http://localhost:8080/project/{id}/timeentries`

### METHOD
`GET`

### EXAMPLE
```curl
curl "http://localhost:8080/project/1/timeentries"
```

### RETURNS
> HTTP Status code: `200`

A list of JSON-encoded dictionaries including an id (`id`), Toggl id (`togglId`), starting date and time (`startDate`),
ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running
state (`running`) and the project name (`projectName`).

#### Sample response

```json
[
  {
    "id": 1,
    "togglId": null,
    "startDate": "2020-04-17T14:28:42",
    "endDate": "2020-04-17T14:31:42",
    "duration": 180,
    "description": null,
    "running": false,
    "projectName": "First Project"
  },
  {
    "id": 2,
    "togglId": null,
    "startDate": "2020-04-17T16:14:29",
    "endDate": null,
    "duration": 1672,
    "description": "Test",
    "running": true,
    "projectName": "First Project"
  }
]
```

## Create a new Project
### URL STRUCTURE
`http://localhost:8080/project`

### METHOD
`POST`

#### Parameters
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

### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/project" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "Third Project",
  "clientName": "First Client"
}'
```

### RETURNS
> HTTP Status code: `201`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).  
> The Toggl ID is obviously `null` at this point.

#### Sample Response
```json
{
    "id": 3,
    "togglId": null,
    "name": "Third Project",
    "billable": false,
    "clientName": "First Client"
}
```

## Update Project
### URL STRUCTURE
`http://localhost:8080/project/{id}`

### METHOD
`PUT`

#### Parameters
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

### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/project/3" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 12,
  "billable": true
}'
```

### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and the client name (`clientName`).

#### Sample Response

```json
{
  "id": 3,
  "togglId": 12,
  "name": "Third Project",
  "billable": true,
  "clientName": "First Client"
}
```

## Delete Project by ID

### URL STRUCTURE

`http://localhost:8080/project/{id}`

### METHOD

`DELETE`

### EXAMPLE

```curl
curl -X "DELETE" "http://localhost:8080/project/1"
```

### RETURNS

> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and
the client name (`clientName`).
> ⚠️ If the Project has still children, you won't be able to delete it.  
> If you still want to delete this Project, you may use a _[force delete](https://github.com/Harchytekt/inTime/blob/master/docs/REST%20API%20Entities/Project.md#force-delete-time-entry-by-id)_.

#### Sample Response

```json
{
  "id": 1,
  "togglId": null,
  "name": "First Project",
  "billable": false,
  "clientName": "First Client"
}
```

## Force Delete Project by ID

### URL STRUCTURE

`http://localhost:8080/project/{id}/force`

### METHOD

`DELETE`

### EXAMPLE

```curl
curl -X "DELETE" "http://localhost:8080/project/1/force"
```

### RETURNS

> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), name (`name`), billable state (`billable`) and
the client name (`clientName`).
> ⚠️ If the Project has still children, you won't be able to delete it.

#### Sample Response

```json
{
  "id": 1,
  "togglId": null,
  "name": "First Project",
  "billable": false,
  "clientName": "First Client"
}
```

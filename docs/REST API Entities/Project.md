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
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), name (`name`), billable state (`billable`), the workspace name (`workspaceName`) and the client name (`clientName`).

##### Sample response
```json
{
    "id": 1,
    "togglId": null,
    "name": "First Project",
    "billable": false,
    "workspaceName": "My workspace",
    "clientName": "First Client"
}
```
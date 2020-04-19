## Client

### Get by id
#### URL STRUCTURE
`http://localhost:8080/client/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/client/1"
```

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), name (`name`) and the workspace name (`workspaceName`).

##### Sample response
```json
{
    "id": 1,
    "togglId": null,
    "name": "First Client",
    "workspaceName": "My workspace"
}
```
## Workspace

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
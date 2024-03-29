# Get All Projects

> Last modified: 2023-08-14 (v0.0.6)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Get a list of all Projects from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /project`

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/project"
```

**Code:** `200 SUCCESS`

**Content:**

```json
[
  {
    "id": 1,
    "name": "My First Project",
    "clientId": 1
  },
  {
    "id": 2,
    "name": "My Second Project",
    "clientId": 1
  }
]
```

### Failure

#### No Project Found

```shell
curl "http://localhost:8080/project"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No entity 'Project' found!",
  "path": "/project"
}
```

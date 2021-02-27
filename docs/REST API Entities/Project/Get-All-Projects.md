# Get All Projects
> Last modified: 26/02/2021 (v0.0.1)

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
    "togglId": null,
    "name": "My First Project",
    "billable": false,
    "clientName": "My First Client"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "My Second Project",
    "billable": false,
    "clientName": "My First Client"
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
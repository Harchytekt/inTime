# Delete Project by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Delete a Project by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `DELETE /project/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Project | ✔️ |

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl -X "DELETE" "http://localhost:8080/project/1"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "togglId": null,
  "name": "My First Project",
  "billable": false,
  "clientName": "My First Client"
}
```

### Failure

#### No Project Found

```shell
curl -X "DELETE" "http://localhost:8080/project/404"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Project' with attribute '404' found!",
  "path": "/project/404"
}
```

#### Existing Child Found

```shell
curl -X "DELETE" "http://localhost:8080/project/2"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "One or more 'Project' still linked to this entity.",
  "path": "/project/2"
}
```
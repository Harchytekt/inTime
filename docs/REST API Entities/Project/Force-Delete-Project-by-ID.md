# Get Project by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Force delete a Project by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `DELETE /project/{id}/force`

## Request parameters

| Name | Type | Description                     | Required |
|:-----|:-----|:--------------------------------|:--------:|
| id   | long | The ID of the requested Project |    ✔️    |

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl -X "DELETE" "http://localhost:8080/project/2/force"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "name": "My Second Project",
  "clientId": 1
}
```

### Failure

#### No Project Found

```shell
curl -X "DELETE" "http://localhost:8080/project/404/force"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Project' with attribute '404' found!",
  "path": "/project/404/force"
}
```

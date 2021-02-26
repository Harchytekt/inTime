# Get Project by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Get a Project by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /project/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Project | ✔️ |

## Response parameters

| Name | Type | Description |
|:--|:--|:--|
| togglId | Long | The ID of the corresponding Toggl Project |
| name | String | The name of the requested Project |
| billable | Boolean | The billability status of the Project |
| clientName | String | The name of the linked Client |

## Sample

### Success

```shell
curl "http://localhost:8080/project/1
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
curl "http://localhost:8080/project/404
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 404,
  "message": "No 'Project' with attribute '404' found!",
  "path": "/project/404"
}
```
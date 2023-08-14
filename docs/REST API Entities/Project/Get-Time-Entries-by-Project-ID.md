# Get Time Entries by Project ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Get a list of all Projects linked to the Project from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /project/{id}/timeentries`

## Request parameters

| Name | Type | Description           | Required |
|:-----|:-----|:----------------------|:--------:|
| id   | Long | The ID of the Project |    ✔️    |

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](../Time%20Entry/Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/project/1/timeentries"
```

**Code:** `200 SUCCESS`

**Content:**

```json
[
  {
    "id": 1,
    "startDate": "2021-06-21T09:41:00",
    "endDate": "2021-06-21T09:45:00",
    "duration": 4,
    "description": "description",
    "running": false,
    "projectId": 1
  },
  {
    "id": 2,
    "startDate": "2021-06-21T09:42:00",
    "endDate": null,
    "duration": 3666,
    "description": null,
    "running": true,
    "projectId": 1
  }
]
```

### Failure

#### No Project Found

```shell
curl "http://localhost:8080/project/404/timeentries"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Project' with attribute '404' found!",
  "path": "/project/404/timeentries"
}
```

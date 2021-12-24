# Delete Time Entry by ID

> Last modified: 24/12/2021 (v0.0.4)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Delete a Time Entry by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `DELETE /time_entry/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | long | The ID of the requested Time Entry | ✔️ |

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "DELETE" "http://localhost:8080/time_entry/1"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "startDate": "2021-06-21T09:41:00",
  "endDate": "2021-06-21T09:45:00",
  "duration": 4,
  "description": "description",
  "running": false,
  "workspaceId": 1,
  "clientId": 1,
  "projectId": 1
}
```

### Failure

#### No Time Entry Found

```shell
curl -X "DELETE" "http://localhost:8080/time_entry/404"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'TimeEntry' with attribute '404' found!",
  "path": "/time_entry/404"
}
```

#### Running Time Entry

```shell
curl -X "DELETE" "http://localhost:8080/time_entry/2"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "The 'TimeEntry' is still running!",
  "path": "/time_entry/2"
}
```

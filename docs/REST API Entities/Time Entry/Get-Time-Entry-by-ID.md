# Get Time Entry by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Get a Time Entry by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /time_entry/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Time Entry | ✔️ |

## Response parameters

| Name | Type | Description |
|:--|:--|:--|
| id | Long | The ID of the requested Time Entry |
| togglId | Long | The ID of the corresponding Toggl Time Entry |
| startDate | DateTime | The start date and time of the Time Entry |
| endDate | DateTime | The end date and time of the Time Entry |
| duration | Long | The duration in milliseconds of the Time Entry |
| description | String | The description of the Time Entry |
| running | Boolean | The running state of the Time Entry |
| projectName | String | The name of the linked Project |

> The duration is calculated at call when the Time Entry is running.

## Sample

### Success

```shell
curl "http://localhost:8080/time_entry/1
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "togglId": null,
  "startDate": "2021-06-21T09:41:00",
  "endDate": "2021-06-21T09:45:00",
  "duration": 4,
  "description": "description",
  "running": false,
  "projectName": "My First Project"
}
```

### Failure

#### No Workspace Found

```shell
curl "http://localhost:8080/time_entry/404
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 404,
  "message": "No 'TimeEntry' with attribute '404' found!",
  "path": "/time_entry/404"
}
```
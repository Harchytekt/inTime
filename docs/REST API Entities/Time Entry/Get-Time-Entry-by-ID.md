# Get Time Entry by ID

> Last modified: 24/12/2021 (v0.0.4)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Get a Time Entry by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /time_entry/{id}`

## Request parameters

| Name | Type | Description                        | Required |
|:-----|:-----|:-----------------------------------|:--------:|
| id   | long | The ID of the requested Time Entry |    ✔️    |

## Response parameters

| Name        | Type     | Description                               |
|:------------|:---------|:------------------------------------------|
| id          | long     | The ID of the requested Time Entry        |
| startDate   | dateTime | The start date and time of the Time Entry |
| endDate     | dateTime | The end date and time of the Time Entry   |
| duration    | long     | The duration in seconds of the Time Entry |
| description | string   | The description of the Time Entry         |
| running     | boolean  | The running state of the Time Entry       |
| projectId   | long     | The ID of the linked Project              |

> The duration is calculated at call when the Time Entry is running.

## Sample

### Success

```shell
curl "http://localhost:8080/time_entry/1"
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
  "projectId": 1
}
```

### Failure

#### No Time Entry Found

```shell
curl "http://localhost:8080/time_entry/404"
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

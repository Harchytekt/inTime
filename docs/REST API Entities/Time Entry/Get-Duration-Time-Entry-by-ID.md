# Get Duration for a Time Entry by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Get the duration of a Time Entry by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /time_entry/{id}/duration`

## Request parameters

| Name | Type | Description                        | Required |
|:-----|:-----|:-----------------------------------|:--------:|
| id   | long | The ID of the requested Time Entry |    ✔️    |

## Response parameters

| Name     | Type | Description                               |
|:---------|:-----|:------------------------------------------|
| duration | long | The duration in seconds of the Time Entry |

> The duration is calculated at call when the Time Entry is running.

## Sample

### Success

```shell
curl "http://localhost:8080/time_entry/1/duration"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "duration": 4
}
```

### Failure

#### No Time Entry Found

```shell
curl "http://localhost:8080/time_entry/404/duration"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'TimeEntry' with attribute '404' found!",
  "path": "/time_entry/404/duration"
}
```

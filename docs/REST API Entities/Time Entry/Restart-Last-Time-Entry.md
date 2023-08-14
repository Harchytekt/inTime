# Restart Last Time Entry

> Last modified: 24/12/2021 (v0.0.4)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Restart the last stopped Time Entry.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /time_entry/restart`

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/time_entry/restart"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "startDate": "2021-06-21T09:42:00",
  "endDate": null,
  "duration": 3742,
  "description": null,
  "running": true,
  "projectId": 1
}
```

### Failure

#### Already Running Time Entry

```shell
curl -X "PUT" "http://localhost:8080/time_entry/restart"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T10:52:30",
  "status": 409,
  "message": "A 'TimeEntry' is already running!",
  "path": "/time_entry/restart"
}
```

# Stop Current Time Entry

> Last modified: 27/02/2021 (v0.0.1)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Stop the Current Time Entry.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /time_entry/stop`

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/time_entry/stop"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "togglId": null,
  "startDate": "2021-06-21T09:42:00",
  "endDate": "2021-06-21T10:52:21",
  "duration": 3666,
  "description": null,
  "running": false,
  "projectName": "My First Project"
}
```

### Failure

#### No Running Time Entry Found

```shell
curl -X "PUT" "http://localhost:8080/time_entry/stop"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T10:52:30",
  "status": 404,
  "message": "No running 'TimeEntry' found!",
  "path": "/time_entry/stop/"
}
```

# Get All Time Entries

> Last modified: 2023-08-14 (v0.0.6)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Get a list of all Time Entries from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /time_entry`

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/time_entry"
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

#### No Time Entry Found

```shell
curl "http://localhost:8080/time_entry"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T10:43:05.000",
  "status": 404,
  "message": "No entity 'TimeEntry' found!",
  "path": "/time_entry"
}
```

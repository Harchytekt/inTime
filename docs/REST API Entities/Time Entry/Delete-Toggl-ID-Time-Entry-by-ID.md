# Delete Toggl ID for Time Entry by ID

> Last modified: 27/02/2021 (v0.0.1)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Remove the linked Toggl ID (set to null).

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /time_entry/{id}/togglid`

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/time_entry/2/togglid"
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

#### No Time Entry Found

```shell
curl "http://localhost:8080/time_entry/404/togglid"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'TimeEntry' with attribute '404' found!",
  "path": "/time_entry/404/togglid"
}
```

#### Toggl ID Already Null

```shell
curl "http://localhost:8080/time_entry/1/togglid"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "There is no Toggl ID linked to the entity 'TimeEntry' with id '1'!",
  "path": "/time_entry/1/togglid"
}
```

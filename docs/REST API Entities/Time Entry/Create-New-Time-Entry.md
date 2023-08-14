# Create a New Time Entry

> Last modified: 24/12/2021 (v0.0.4)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Create a new Time Entry on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /time_entry`

## Request parameters

| Name        | Type   | Description                       | Required |
|:------------|:-------|:----------------------------------|:--------:|
| description | string | The description of the Time Entry |    ❌     |
| projectId   | long   | The ID of the linked Project      |    ✔️    |
| projectName | String | The name of the linked Project    |    ✔️    |

> Only one of `projectId` and `projectName` is required

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "POST" "http://localhost:8080/time_entry" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "projectId": 1
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "startDate": "2021-06-21T10:52:21",
  "endDate": null,
  "duration": 0,
  "description": null,
  "running": true,
  "projectId": 1
}
```

### Failure

#### No Project Found or Given

```shell
curl -X "POST" "http://localhost:8080/time_entry" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My Project"
}'
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T10:52:21",
  "status": 404,
  "message": "No 'Project' with attribute 'null' found!",
  "path": "/time_entry/"
}
```

#### Missing required field

```shell
curl -X "POST" "http://localhost:8080/time_entry" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "description": "Really important description"
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "Missing the field 'workspaceId' to create the entity 'TimeEntry'!",
  "path": "/time_entry/"
}
```

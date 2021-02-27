# Create a New Tme Entry

> Last modified: 27/02/2021 (v0.0.1)

Back to [Tme Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Create a new Tme Entry on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /time_entry`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| description | String | The description of the Time Entry | ❌ |
| projectName | String | The name of the linked Project | ✔️ |

## Response parameters

For the description of the Tme Entry entity, see [Get Tme Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "POST" "http://localhost:8080/time_entry" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "projectName": "My First Project"
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "togglId": null,
  "startDate": "2021-06-21T10:52:21",
  "endDate": null,
  "duration": 0,
  "description": null,
  "running": true,
  "projectName": "My First Project"
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
  "message": "Missing the field 'projectName' to create the entity 'TimeEntry'!",
  "path": "/time_entry/"
}
```
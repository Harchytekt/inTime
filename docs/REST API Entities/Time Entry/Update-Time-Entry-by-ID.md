# Update Time Entry by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Update a Time Entry by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /time_entry/{id}`

## Request parameters

| Name        | Type     | Description                               | Required |
|:------------|:---------|:------------------------------------------|:--------:|
| description | string   | The description of the Time Entry         |    ❌     |
| projectId   | long     | The ID of the linked Project              |    ❌     |
| projectName | string   | The name of the linked Project            |    ❌     |
| startDate   | dateTime | The start date and time of the Time Entry |    ❌     |
| endDate     | dateTime | The end date and time of the Time Entry   |    ❌     |

> At least one of those fields is required.

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/time_entry/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "projectId": 2
}'
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
  "projectId": 2
}
```

### Failure

#### No Time Entry Found

```shell
curl -X "PUT" "http://localhost:8080/time_entry/404" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "projectId": 3
}'
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

#### No data sent

```shell
curl -X "PUT" "http://localhost:8080/time_entry/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 400,
  "message": "The entity 'TimeEntry' with attribute '1' couldn't be updated! Nothing was sent in the body.",
  "path": "/time_entry/1"
}
```

#### No change

```shell
curl -X "PUT" "http://localhost:8080/time_entry/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "description": "description",
}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 400,
  "message": "The entity 'TimeEntry' with attribute '1' couldn't be updated! Please check the changes you've made.",
  "path": "/time_entry/1"
}
```

#### Conflict on endDate <= startDate

```shell
curl -X "PUT" "http://localhost:8080/time_entry/2" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "endDate": "2021-06-01T00:00:42"
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T10:53:00",
  "status": 409,
  "message": "The end date should be after the start date: '2021-06-21T09:41:00' > '2021-06-01T00:00:42'!",
  "path": "/time_entry/2"
}
```

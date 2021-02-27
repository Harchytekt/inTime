# Update Time Entry by ID

> Last modified: 27/02/2021 (v0.0.1)

Back to [Time Entry](../Time%20Entry.md) | to [Summary](../../README.md)

Update a Time Entry by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /time_entry/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| togglId | Long | The ID of the corresponding Toggl Time Entry | ❌ |
| startDate | DateTime | The start date and time of the Time Entry | ❌ |
| endDate | DateTime | The end date and time of the Time Entry | ❌ |
| description | String | The description of the Time Entry | ❌ |
| projectName | String | The name of the linked Project | ❌ |

> At least one of the five fields is needed.  
> ⚠️ The Time Entry has to be stopped to have an endDate (this will be changed).  
> ⚠️ There is no check on the endDate for now (endDate < startDate)…

## Response parameters

For the description of the Time Entry entity, see [Get Time Entry by ID](Get-Time-Entry-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/time_entry/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 1
}'
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "togglId": 1,
  "startDate": "2021-06-21T09:41:00",
  "endDate": "2021-06-21T09:45:00",
  "duration": 4,
  "description": "description",
  "running": false,
  "projectName": "My First Project"
}
```

### Failure

#### No Time Entry Found

```shell
curl -X "PUT" "http://localhost:8080/time_entry/404" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 1
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
  "projectName": "My First Project"
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

#### Conflict when adding an endDate

```shell
curl -X "PUT" "http://localhost:8080/time_entry/2" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "endDate": "2021-06-21T10:52:21"
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T10:53:00",
  "status": 409,
  "message": "The 'TimeEntry' is still running!",
  "path": "/time_entry/2"
}
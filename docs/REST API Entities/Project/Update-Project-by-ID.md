# Update Project by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Update a Project by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /project/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| togglId | Long | The ID of the corresponding Toggl Project | ❌ |
| name | String | The name of the requested Project | ❌ |
| billable | Boolean | The billability status of the Project | ❌ |
| clientName | String | The name of the linked Client | ❌ |

> At least one of the four fields is needed.

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/project/1" \
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
  "name": "My First Project",
  "billable": false,
  "clientName": "My First Client"
}
```

### Failure

#### No Project Found

```shell
curl -X "PUT" "http://localhost:8080/project/404" \
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
  "message": "No 'Project' with attribute '404' found!",
  "path": "/project/404"
}
```

#### No data sent

```shell
curl -X "PUT" "http://localhost:8080/project/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 400,
  "message": "The entity 'Project' with attribute '1' couldn't be updated! Nothing was sent in the body.",
  "path": "/project/1"
}
```

#### No change

```shell
curl -X "PUT" "http://localhost:8080/project/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Project"
}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 400,
  "message": "The entity 'Project' with attribute '1' couldn't be updated! Please check the changes you've made.",
  "path": "/project/1"
}
```
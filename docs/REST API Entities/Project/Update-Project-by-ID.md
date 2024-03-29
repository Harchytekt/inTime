# Update Project by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Update a Project by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /project/{id}`

## Request parameters

| Name       | Type   | Description                       | Required |
|:-----------|:-------|:----------------------------------|:--:|
| name       | string | The name of the requested Project | ❌ |
| clientId   | long   | The ID of the linked Client       | ❌ |
| clientName | string | The name of the linked Client     | ❌ |

> At least one of those fields is required.

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/project/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "clientId": 2
}'
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "name": "My First Project",
  "clientId": 2
}
```

### Failure

#### No Project Found

```shell
curl -X "PUT" "http://localhost:8080/project/404" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "clientId": 2
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

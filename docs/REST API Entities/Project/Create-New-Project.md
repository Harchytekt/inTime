# Create a New Project

> Last modified: 24/12/2021 (v0.0.4)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Create a new Project on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /project`

## Request parameters

| Name       | Type   | Description                       | Required |
|:-----------|:-------|:----------------------------------|:--------:|
| name       | string | The name of the requested Project |    ✔️    |
| clientId   | long   | The ID of the linked Client       |    ✔️    |
| clientName | string | The name of the linked Client     |    ✔️    |

> Only one of `clientId` and `clientName` is required

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Samples

### Success

```shell
curl -X "POST" "http://localhost:8080/cprojectlient" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My Third Project",
  "clientId": 1
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "name": "My Third Project",
  "clientId": 1
}
```

### Failure

#### Conflict (duplicate entry)

```shell
curl -X "POST" "http://localhost:8080/project" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Project"
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "An entity 'Project' with 'name' 'My First Project' already exist!",
  "path": "/project/"
}
```

#### Missing required field

```shell
curl -X "POST" "http://localhost:8080/project" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "clientId": 1
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "Missing the field 'name' to create the entity 'Project'!",
  "path": "/project/"
}
```

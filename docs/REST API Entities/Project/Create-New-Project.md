# Create a New Project

> Last modified: 26/02/2021 (v0.0.1)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Create a new Project on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /project`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| togglId | Long | The ID of the corresponding Toggl Project | ❌ |
| name | String | The name of the requested Project | ✔️ |
| billable | Boolean | The billability status of the Project | ❌ |
| clientName | String | The name of the linked Client | ✔️ |

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl -X "POST" "http://localhost:8080/cprojectlient" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My Third Project"
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "togglId": null,
  "name": "My Third Project",
  "billable": false,
  "clientName": null
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
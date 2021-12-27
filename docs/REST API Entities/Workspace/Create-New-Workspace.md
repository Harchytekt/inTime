# Create a New Workspace

> Last modified: 24/12/2021 (v0.0.4)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Create a new Workspace on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /workspace`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| name | String | The name of the requested Workspace | ✔️ |

## Response parameters

For the description of the Workspace entity, see [Get Workspace by ID](Get-Workspace-by-ID.md).

## Sample

### Success

```shell
curl -X "POST" "http://localhost:8080/workspace" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My Third Workspace"
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "name": "My Third Workspace"
}
```

### Failure

#### Conflict (duplicate entry)

```shell
curl -X "POST" "http://localhost:8080/workspace" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Workspace"
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "An entity 'Workspace' with 'name' 'My First Workspace' already exist!",
  "path": "/workspace/"
}
```

#### Missing required field

```shell
curl -X "POST" "http://localhost:8080/workspace" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "Missing the field 'name' to create the entity 'Workspace'!",
  "path": "/workspace/"
}
```
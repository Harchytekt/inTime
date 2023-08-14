# Create a New Client

> Last modified: 24/12/2021 (v0.0.4)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Create a new Client on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /client`

## Request parameters

| Name          | Type   | Description                      | Required |
|:--------------|:-------|:---------------------------------|:--------:|
| name          | string | The name of the requested Client |    ✔️    |
| workspaceId   | long   | The ID of the linked Workspace   |    ✔️    |
| workspaceName | string | The name of the linked Workspace |    ✔️    |

> Only one of `workspaceId` and `workspaceName` is required

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl -X "POST" "http://localhost:8080/client" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My Third Client",
  "workspaceId": 1
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "name": "My Third Client",
  "workspaceId": 1
}
```

### Failure

#### Conflict (duplicate entry)

```shell
curl -X "POST" "http://localhost:8080/client" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Client",
  "workspaceId": 1
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "An entity 'Client' with 'name' 'My First Client' already exist!",
  "path": "/client/"
}
```

#### Missing required field

```shell
curl -X "POST" "http://localhost:8080/client" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "workspaceId": 1
}'
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "Missing the field 'name' to create the entity 'Client'!",
  "path": "/client/"
}
```

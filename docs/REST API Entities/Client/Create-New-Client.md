# Create a New Client

> Last modified: 26/02/2021 (v0.0.1)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Create a new Client on the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `POST /client`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| togglId | Long | The ID of the corresponding Toggl Client | ❌ |
| name | String | The name of the requested Client | ✔️ |
| workspaceName | String | The name of the linked Workspace | ✔️ |

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl -X "POST" "http://localhost:8080/client" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My Third Client"
}'
```

**Code:** `201 CREATED`

**Content:**

```json
{
  "id": 3,
  "togglId": null,
  "name": "My Third Client",
  "workspaceName": null
}
```

### Failure

#### Conflict (duplicate entry)

```shell
curl -X "POST" "http://localhost:8080/client" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Client"
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
  "togglId": 1
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
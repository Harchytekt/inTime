# Delete Client by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Delete a Client by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `DELETE /client/{id}`

## Request parameters

| Name | Type | Description                    | Required |
|:-----|:-----|:-------------------------------|:--------:|
| id   | long | The ID of the requested Client |    ✔️    |

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl -X "DELETE" "http://localhost:8080/client/1"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "name": "My First Client",
  "workspaceId": 1
}
```

### Failure

#### No Client Found

```shell
curl -X "DELETE" "http://localhost:8080/client/404"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Client' with attribute '404' found!",
  "path": "/client/404"
}
```

#### Existing Child Found

```shell
curl -X "DELETE" "http://localhost:8080/client/2"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "One or more 'Client' still linked to this entity.",
  "path": "/client/2"
}
```

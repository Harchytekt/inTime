# Get Client by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Get a Client by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /client/{id}`

## Request parameters

| Name | Type | Description                    | Required |
|:-----|:-----|:-------------------------------|:--------:|
| id   | long | The ID of the requested Client |    ✔️    |

## Response parameters

| Name        | Type   | Description                      |
|:------------|:-------|:---------------------------------|
| id          | long   | The ID of the requested Client   |
| name        | string | The name of the requested Client |
| workspaceId | long   | The ID of the linked Workspace   |

## Sample

### Success

```shell
curl "http://localhost:8080/client/1"
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
curl "http://localhost:8080/client/404"
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

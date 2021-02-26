# Get Client by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Get a Client by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /client/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Client | ✔️ |

## Response parameters

| Name | Type | Description |
|:--|:--|:--|
| id | Long | The ID of the requested Client |
| togglId | Long | The ID of the corresponding Toggl Client |
| name | String | The name of the requested Client |
| workspaceName | String | The name of the linked Workspace |

## Sample

### Success

```shell
curl "http://localhost:8080/client/1
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "togglId": null,
  "name": "My First Client",
  "workspaceName": "My First Workspace"
}
```

### Failure

#### No Client Found

```shell
curl "http://localhost:8080/client/404
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 404,
  "message": "No 'Client' with attribute '404' found!",
  "path": "/client/404"
}
```
# Get Workspace by ID

> Last modified: 24/12/2021 (v0.0.4)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Get a Workspace by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /workspace/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Workspace | ✔️ |

## Response parameters

| Name | Type | Description |
|:--|:--|:--|
| id | Long | The ID of the requested Workspace |
| name | String | The name of the requested Workspace |

## Sample

### Success

```shell
curl "http://localhost:8080/workspace/1"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "name": "My First Workspace"
}
```

### Failure

#### No Workspace Found

```shell
curl "http://localhost:8080/workspace/404"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Workspace' with attribute '404' found!",
  "path": "/workspace/404"
}
```
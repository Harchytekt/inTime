# Get Workspace by ID

> Last modified: 2023-08-14 (v0.0.6)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Force delete a Workspace by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `DELETE /workspace/{id}/force`

## Request parameters

| Name | Type | Description                       | Required |
|:-----|:-----|:----------------------------------|:--------:|
| id   | long | The ID of the requested Workspace |    ✔️    |

## Response parameters

For the description of the Workspace entity, see [Get Workspace by ID](Get-Workspace-by-ID.md).

## Sample

### Success

```shell
curl -X "DELETE" "http://localhost:8080/workspace/2/force"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "name": "My Second Workspace"
}
```

### Failure

#### No Workspace Found

```shell
curl -X "DELETE" "http://localhost:8080/workspace/404/force"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Workspace' with attribute '404' found!",
  "path": "/workspace/404/force"
}
```

# Get Clients by Workspace ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Get a list of all Clients linked to the Workspace from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /workspace/{id}/clients`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the Workspace | ✔️ |

## Response parameters

For the description of the Client entity, see [Get Client by ID](../Client/Get-Client-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/workspace/1/clients
```

**Code:** `200 SUCCESS`

**Content:**

```json
[
  {
    "id": 1,
    "togglId": null,
    "name": "My First Client",
    "workspaceName": "Workspace name"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "My Second Client",
    "workspaceName": "Workspace name"
  }
]
```

### Failure

#### No Workspace Found

```shell
curl "http://localhost:8080/workspace/404/clients
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 404,
  "message": "No 'Workspace' with attribute '404' found!",
  "path": "/workspace/404/clients"
}
```
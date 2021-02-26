# Get Client by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Force delete a Client by ID from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `DELETE /client/{id}/force`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Client | ✔️ |

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl -X "DELETE" "http://localhost:8080/client/2/force
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "togglId": null,
  "name": "My Second Client",
  "workspaceName": "My First Workspace"
}
```

### Failure

#### No Client Found

```shell
curl -X "DELETE" "http://localhost:8080/client/404/force
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 404,
  "message": "No 'Client' with attribute '404' found!",
  "path": "/client/404/force"
}
```
# Get All Clients
> Last modified: 26/02/2021 (v0.0.1)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Get a list of all Clients from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /client`

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/client
```

**Code:** `200 SUCCESS`

**Content:**

```json
[
  {
    "id": 1,
    "togglId": null,
    "name": "My First Client",
    "workspaceName": "My First Workspace"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "My Second Client",
    "workspaceName": "My First Workspace"
  }
]
```

### Failure

#### No Client Found

```shell
curl "http://localhost:8080/client
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No entity 'Client' found!",
  "path": "/client"
}
```
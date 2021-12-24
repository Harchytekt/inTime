# Get Projects by Client ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Get a list of all Projects linked to the Client from the server.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `GET /client/{id}/projects`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | long | The ID of the Client | ✔️ |

## Response parameters

For the description of the Project entity, see [Get Project by ID](../Projects/Get-Project-by-ID.md).

## Sample

### Success

```shell
curl "http://localhost:8080/client/1/projects"
```

**Code:** `200 SUCCESS`

**Content:**

```json
[
  {
    "id": 1,
    "togglId": null,
    "name": "My First Project",
    "billable": false,
    "clientName": "My First Client"
  },
  {
    "id": 2,
    "togglId": null,
    "name": "My Second Project",
    "billable": false,
    "clientName": "My First Client"
  }
]
```

### Failure

#### No Client Found

```shell
curl "http://localhost:8080/client/404/projects"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Client' with attribute '404' found!",
  "path": "/client/404/projects"
}
```

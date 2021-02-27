# Delete Toggl ID for Client by ID

> Last modified: 27/02/2021 (v0.0.1)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Remove the linked Toggl ID (set to null).

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /client/{id}/togglid`

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/client/2/togglid"
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
curl "http://localhost:8080/client/404/togglid"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Client' with attribute '404' found!",
  "path": "/client/404/togglid"
}
```

#### Toggl ID Already Null

```shell
curl "http://localhost:8080/client/1/togglid"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "There is no Toggl ID linked to the entity 'Client' with id '1'!",
  "path": "/client/1/togglid"
}
```

# Delete Toggl ID for Workspace by ID

> Last modified: 27/02/2021 (v0.0.1)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Remove the linked Toggl ID (set to null).

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /workspace/{id}/togglid`

## Response parameters

For the description of the Workspace entity, see [Get Workspace by ID](Get-Workspace-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/workspace/2/togglid"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "togglId": null,
  "name": "My Second Workspace"
}
```

### Failure

#### No Workspace Found

```shell
curl "http://localhost:8080/workspace/404/togglid"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Workspace' with attribute '404' found!",
  "path": "/workspace/404/togglid"
}
```

#### Toggl ID Already Null

```shell
curl "http://localhost:8080/workspace/1/togglid"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "There is no Toggl ID linked to the entity 'Workspace' with id '1'!",
  "path": "/workspace/1/togglid"
}
```

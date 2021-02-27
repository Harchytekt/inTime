# Delete Toggl ID for Project by ID

> Last modified: 27/02/2021 (v0.0.1)

Back to [Project](../Project.md) | to [Summary](../../README.md)

Remove the linked Toggl ID (set to null).

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /project/{id}/togglid`

## Response parameters

For the description of the Project entity, see [Get Project by ID](Get-Project-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/project/2/togglid"
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 2,
  "togglId": null,
  "name": "My Second Project",
  "billable": false,
  "clientName": "My First Client"
}
```

### Failure

#### No Project Found

```shell
curl "http://localhost:8080/project/404/togglid"
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Project' with attribute '404' found!",
  "path": "/project/404/togglid"
}
```

#### Toggl ID Already Null

```shell
curl "http://localhost:8080/project/1/togglid"
```

**Code:** `409 CONFLICT`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 409,
  "message": "There is no Toggl ID linked to the entity 'Project' with id '1'!",
  "path": "/project/1/togglid"
}
```

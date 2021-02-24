# Update Workspace by ID

> Last modified: 24/02/2021 (v0.0.1)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Update a Workspace by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /workspace/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| id | Long | The ID of the requested Workspace | ✔️ |

## Response parameters

| Name | Type | Description |
|:--|:--|:--|
| togglId | Long | The ID of the corresponding Toggl Workspace |
| name | String | The name of the requested Workspace |

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/workspace/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 1
}'
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "togglId": 1,
  "name": "My First Workspace"
}
```

### Failure

#### No Workspace Found

```shell
curl -X "PUT" "http://localhost:8080/workspace/404" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 1
}'
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 404,
  "message": "No 'Workspace' with attribute '404' found!",
  "path": "/workspace/404"
}
```
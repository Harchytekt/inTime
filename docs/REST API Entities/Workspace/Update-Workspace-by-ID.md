# Update Workspace by ID

> Last modified: 26/02/2021 (v0.0.1)

Back to [Workspace](../Workspace.md) | to [Summary](../../README.md)

Update a Workspace by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /workspace/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| togglId | Long | The ID of the corresponding Toggl Workspace | ❌ |
| name | String | The name of the requested Workspace | ❌ |

> At least one of the two is needed.

## Response parameters

For the description of the Workspace entity, see [Get Workspace by ID](Get-Workspace-by-ID.md).

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

#### No data sent

```shell
curl -X "PUT" "http://localhost:8080/workspace/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 400,
  "message": "The entity 'Workspace' with attribute '1' couldn't be updated! Nothing was sent in the body.",
  "path": "/workspace/1"
}
```

#### No change

```shell
curl -X "PUT" "http://localhost:8080/workspace/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Workspace"
}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000+0000",
  "status": 400,
  "message": "The entity 'Workspace' with attribute '1' couldn't be updated! Please check the changes you've made.",
  "path": "/workspace/1"
}
```
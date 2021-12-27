# Update Client by ID

> Last modified: 24/12/2021 (v0.0.4)

Back to [Client](../Client.md) | to [Summary](../../README.md)

Update a Client by ID.

**Auth required:** _No_  
**Permissions required:** _None_  
**Endpoint's URL:** `PUT /client/{id}`

## Request parameters

| Name | Type | Description | Required |
|:--|:--|:--|:--:|
| name | String | The name of the requested Client | ❌ |
| workspaceId | long | The ID of the linked Workspace | ❌ |

> At least one of the three fields is needed.

## Response parameters

For the description of the Client entity, see [Get Client by ID](Get-Client-by-ID.md).

## Sample

### Success

```shell
curl -X "PUT" "http://localhost:8080/client/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "workspaceId": 2
}'
```

**Code:** `200 SUCCESS`

**Content:**

```json
{
  "id": 1,
  "name": "My First Client",
  "workspaceId": 2
}
```

### Failure

#### No Client Found

```shell
curl -X "PUT" "http://localhost:8080/client/404" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "workspaceId": 1
}'
```

**Code:** `404 NOT FOUND`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 404,
  "message": "No 'Client' with attribute '404' found!",
  "path": "/client/404"
}
```

#### No data sent

```shell
curl -X "PUT" "http://localhost:8080/client/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 400,
  "message": "The entity 'Client' with attribute '1' couldn't be updated! Nothing was sent in the body.",
  "path": "/client/1"
}
```

#### No change

```shell
curl -X "PUT" "http://localhost:8080/client/1" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "name": "My First Client"
}'
```

**Code:** `400 BAD REQUEST`

**Content:**

```json
{
  "timestamp": "2021-06-21T09:41:00.000",
  "status": 400,
  "message": "The entity 'Client' with attribute '1' couldn't be updated! Please check the changes you've made.",
  "path": "/client/1"
}
```

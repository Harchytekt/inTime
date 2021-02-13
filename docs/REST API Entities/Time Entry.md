# Time Entry

### Get Time Entry by ID
#### URL STRUCTURE
`http://localhost:8080/time_entry/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/time_entry/1"
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
The duration is calculated at call when the Time Entry is running.

##### Sample response
```json
{
    "id": 1,
    "togglId": null,
    "startDate": "2020-04-17T14:28:42",
    "endDate": null,
    "duration": 6347,
    "description": null,
    "running": true,
    "projectName": "First Project"
}
```

### Get duration
#### URL STRUCTURE
`http://localhost:8080/time_entry/{id}/duration`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/time_entry/1/duration"
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including the duration in milliseconds (`duration`).  
The duration is calculated at call when the Time Entry is running.

##### Sample Response
```json
{
    "duration": 6347
}
```

### Create a new Time Entry
#### URL STRUCTURE
`http://localhost:8080/time_entry`

#### METHOD
`POST`

##### Parameters
```json
{
    "projectName": "Project name",
    "description": "description"
}
```

- `projectName` _**String**_: The name of the project to which the Time Entry belongs.
- `description` _**String**_: The description of the Time Entry. The default for this field is `""`.

#### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/time_entry/" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "projectName": "First Project",
  "description": "Test"
}'
```

#### RETURNS
> HTTP Status code: `201`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
> The duration is calculated at call, its value is obviously `0`.  
> The Toggl ID is obviously `null` at this point.

##### Sample Response
```json
{
    "id": 2,
    "togglId": null,
    "startDate": "2020-04-17T16:14:29",
    "endDate": null,
    "duration": 0,
    "description": "Test",
    "running": true,
    "projectName": "First Project"
}
```

### Update Time Entry
#### URL STRUCTURE
`http://localhost:8080/time_entry/{id}`

#### METHOD
`PUT`

##### Parameters
```json
{
    "togglId": 1,
    "projectName": "Project name",
    "description": "Description",
    "startDate": "2020-01-01T00:00:00",
    "endDate": "2020-01-01T00:00:01"
}
```

- `togglId` _**Long?**_: The ID of the Time Entry in Toggl.
- `projectName` _**String?**_: The name of the project to which the Time Entry belongs.
- `description` _**String?**_: The description of the Time Entry. The default for this field is `""`.
- `startDate` _**Date?**_: The starting date and time of the Time Entry.
- `endDate` _**Date?**_: The ending date and time of the Time Entry.
> ⚠️ If the Time Entry is still running, you won't be able to change the `endDate`.

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/time_entry/2" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 42,
  "projectName": "Second Project",
  "description": "Test with update",
  "startDate": "2020-04-17T16:20:56"
}'
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
The duration is calculated at call when the Time Entry is running.

##### Sample Response
```json
{
    "id": 2,
    "togglId": 42,
    "startDate": "2020-04-17T16:20:56",
    "endDate": null,
    "duration": 168,
    "description": "Test with update",
    "running": true,
    "projectName": "Second Project"
}
```

### Stop Current Time Entry
#### URL STRUCTURE
`http://localhost:8080/time_entry/stop`

#### METHOD
`PUT`

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/time_entry/stop"
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).

##### Sample Response
```json
{
    "id": 2,
    "togglId": null,
    "startDate": "2020-04-17T16:14:29",
    "endDate": "2020-04-17T16:21:29",
    "duration": 420,
    "description": "Test",
    "running": false,
    "projectName": "First Project"
}
```

### Restart Last Time Entry
#### URL STRUCTURE
`http://localhost:8080/time_entry/restart`

#### METHOD
`PUT`

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/time_entry/restart"
```

#### RETURNS
> HTTP Status code: `200`

A JSON-encoded dictionary including an ID (`id`), Toggl ID (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
The duration is calculated at call.

##### Sample Response
```json
{
    "id": 2,
    "togglId": null,
    "startDate": "2020-04-17T16:14:29",
    "endDate": null,
    "duration": 635,
    "description": "Test",
    "running": true,
    "projectName": "First Project"
}
```

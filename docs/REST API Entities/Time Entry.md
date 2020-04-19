## Time Entry

### Get by id
#### URL STRUCTURE
`http://localhost:8080/time_entry/{id}`

#### METHOD
`GET`

#### EXAMPLE
```curl
curl "http://localhost:8080/time_entry/1"
```

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
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
`http://localhost:8080/time_entry/`

#### METHOD
`POST`

#### EXAMPLE
```curl
curl -X "POST" "http://localhost:8080/time_entry/" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "projectName": "First Project",
  "description": "Test"
}'
```

##### Parameters
```json
{
    "projectName": "First Project",
    "description": "Test"
}
```

- `projectName` _**String**_: The name of the project to which the Time Entry belongs.
- `description` _**String**_: The description of the Time Entry. The default for this field is `""`.

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
The duration is calculated at call, its value is obviously `0`.

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

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/time_entry/2" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "togglId": 12,
  "projectName": "Second Project",
  "description": "Test with update",
  "startDate": "2020-04-17T16:20:56"
}'
```

##### Parameters
```json
{
    "togglId": 1,
    "projectName": "Project Name",
    "description": "Description",
    "startDate": "2020-01-01T00:00:00",
    "endDate": "2020-01-01T00:00:01"
}
```

- `togglId` _**Long?**_: The id of the Time Entry in Toggl.
- `projectName` _**String?**_: The name of the project to which the Time Entry belongs.
- `description` _**String?**_: The description of the Time Entry. The default for this field is `""`.
- `startDate` _**Date?**_: The starting date and time of the Time Entry.
- `endDate` _**Date?**_: The ending date and time of the Time Entry.
> ⚠️ If the Time Entry is still running, you won't be able to change the `endDate`.

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
The duration is calculated at call when the Time Entry is running.

##### Sample Response 
```json
{
    "id": 2,
    "togglId": 12,
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
`http://localhost:8080/time_entry/stop/`

#### METHOD
`PUT`

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/time_entry/stop/"
```

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).

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
`http://localhost:8080/time_entry/restart/`

#### METHOD
`PUT`

#### EXAMPLE
```curl
curl -X "PUT" "http://localhost:8080/time_entry/restart/"
```

#### RETURNS
A JSON-encoded dictionary including an id (`id`), Toggl id (`togglId`), starting date and time (`startDate`), ending date and time (`endDate`), duration in milliseconds (`duration`), description (`description`), running state (`running`) and the project name (`projectName`).  
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

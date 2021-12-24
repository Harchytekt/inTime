# Time Entry

Back to the [Summary](../README.md)

Time entry has the following properties:
- `description`: a description of the time entry (`string`)
- `workspaceId`: workspace ID (`long`, _required if no `client` or `project` supplied_)
- `clientId`: client ID (`long`)
- `projectId`: project ID (`long`)
- `startDate`: time entry start time (`datetime`, _required_)
- `endDate`: time entry end time (`datetime`)
- `duration`: time entry duration in seconds (`long`)
- `running`: whether the time entry is running or not (`boolean`)

## Summary

- [Create a New Time Entry (`POST /time_entry/{id}`) 🔗](Time%20Entry/Create-New-Time-Entry.md)
- [Delete Time Entry by ID (`DELETE /time_entry/{id}`) 🔗](Time%20Entry/Delete-Time-Entry-by-ID.md)
- [Force Delete Time Entry by ID (`DELETE /time_entry/{id}/force`) 🔗](Time%20Entry/Force-Delete-Time-Entry-by-ID.md)
- [Get All Time Entries (`GET /time_entry`) 🔗](Time%20Entry/Get-All-Time-Entries.md)
- [Get Time Entry by ID (`GET /time_entry/{id}`) 🔗](Time%20Entry/Get-Time-Entry-by-ID.md)
- [Get Duration for a Time Entry by ID (`GET /time_entry/{id}/duration`) 🔗](Time%20Entry/Get-Duration-Time-Entry-by-ID.md)
- [Stop Current Time Entry (`PUT /time_entry/stop`) 🔗](Time%20Entry/Stop-Current-Time-Entry.md)
- [Restart Last Time Entry (`PUT /time_entry/restart`) 🔗](Time%20Entry/Restart-Last-Time-Entry.md)
- [Update Time Entry by ID (`PUT /time_entry/{id}`) 🔗](Time%20Entry/Update-Time-Entry-by-ID.md)

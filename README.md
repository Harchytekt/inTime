# inTime
It is a Time Tracking project to practice the development of a Spring app with Groovy.

## Docker
It is possible to run the app in Docker.

To do that, you'll have to compile the project to a `.jar` (`gradlew :bootJar`).  
It'll be located in `build/libs` and it's name will be like `inTime-0.0.5.jar`.

To launch the containers - Spring Boot JDK8 and MySQL 8 (_empty DB_) - execute `docker-compose up -d`.  
To stop them, run `docker-compose down`.

## Doc
- [Workspace](docs/REST%20API%20Entities/Workspace.md)
	- [Create a New Workspace (`POST /workspace/{id}`) 🔗](docs/REST%20API%20Entities/Workspace/Create-New-Workspace.md)
	- [Delete Workspace by ID (`DELETE /workspace/{id}`) 🔗](docs/REST%20API%20Entities/Workspace/Delete-Workspace-by-ID.md)
	- [Force Delete Workspace by ID (`DELETE /workspace/{id}/force`) 🔗](docs/REST%20API%20Entities/Workspace/Force-Delete-Workspace-by-ID.md)
	- [Get All Workspaces (`GET /workspace`) 🔗](docs/REST%20API%20Entities/Workspace/Get-All-Workspaces.md)
	- [Get Workspace by ID (`GET /workspace/{id}`) 🔗](docs/REST%20API%20Entities/Workspace/Get-Workspace-by-ID.md)
	- [Get Clients by Workspace ID (`GET /workspace/{id}/clients`) 🔗](docs/REST%20API%20Entities/Workspace/Get-Clients-by-Workspace-ID.md)
	- [Update Workspace by ID (`PUT /workspace/{id}`) 🔗](docs/REST%20API%20Entities/Workspace/Update-Workspace-by-ID.md)
- [Client](docs/REST%20API%20Entities/Client.md)
	- [Create a New Client (`POST /client/{id}`) 🔗](docs/REST%20API%20Entities/Client/Create-New-Client.md)
	- [Delete Client by ID (`DELETE /client/{id}`) 🔗](docs/REST%20API%20Entities/Client/Delete-Client-by-ID.md)
	- [Force Delete Client by ID (`DELETE /client/{id}/force`) 🔗](docs/REST%20API%20Entities/Client/Force-Delete-Client-by-ID.md)
	- [Get All Clients (`GET /client`) 🔗](docs/REST%20API%20Entities/Client/Get-All-Clients.md)
	- [Get Client by ID (`GET /client/{id}`) 🔗](docs/REST%20API%20Entities/Client/Get-Client-by-ID.md)
	- [Get Projects by Client ID (`GET /client/{id}/projects`) 🔗](docs/REST%20API%20Entities/Client/Get-Projects-by-Client-ID.md)
	- [Update Client by ID (`PUT /client/{id}`) 🔗](docs/REST%20API%20Entities/Client/Update-Client-by-ID.md)
- [Project](docs/REST%20API%20Entities/Project.md)
	- [Create a New Project (`POST /project/{id}`) 🔗](docs/REST%20API%20Entities/Project/Create-New-Project.md)
	- [Delete Project by ID (`DELETE /project/{id}`) 🔗](docs/REST%20API%20Entities/Project/Delete-Project-by-ID.md)
	- [Force Delete Project by ID (`DELETE /project/{id}/force`) 🔗](docs/REST%20API%20Entities/Project/Force-Delete-Project-by-ID.md)
	- [Get All Projects (`GET /project`) 🔗](docs/REST%20API%20Entities/Project/Get-All-Projects.md)
	- [Get Project by ID (`GET /project/{id}`) 🔗](docs/REST%20API%20Entities/Project/Get-Project-by-ID.md)
	- [Get Time Entries by Project ID (`GET /project/{id}/timeentries`) 🔗](docs/REST%20API%20Entities/Project/Get-Time-Entries-by-Project-ID.md)
	- [Update Project by ID (`PUT /project/{id}`) 🔗](docs/REST%20API%20Entities/Project/Update-Project-by-ID.md)
- [Time Entry](docs/REST%20API%20Entities/Time%20Entry.md)
	- [Create a New Time Entry (`POST /time_entry/{id}`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Create-New-Time-Entry.md)
	- [Delete Time Entry by ID (`DELETE /time_entry/{id}`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Delete-Time-Entry-by-ID.md)
	- [Force Delete Time Entry by ID (`DELETE /time_entry/{id}/force`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Force-Delete-Time-Entry-by-ID.md)
	- [Get All Time Entries (`GET /time_entry`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Get-All-Time-Entries.md)
	- [Get Time Entry by ID (`GET /time_entry/{id}`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Get-Time-Entry-by-ID.md)
	- [Get Duration for a Time Entry by ID (`GET /time_entry/{id}/duration`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Get-Duration-Time-Entry-by-ID.md)
	- [Stop Current Time Entry (`PUT /time_entry/stop`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Stop-Current-Time-Entry.md)
	- [Restart Last Time Entry (`PUT /time_entry/restart`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Restart-Last-Time-Entry.md)
	- [Update Time Entry by ID (`PUT /time_entry/{id}`) 🔗](docs/REST%20API%20Entities/Time%20Entry/Update-Time-Entry-by-ID.md)

package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.client.ClientCreateDto
import be.ducobu.inTime.dto.client.ClientDto
import be.ducobu.inTime.dto.client.ClientSaveDto
import be.ducobu.inTime.dto.project.ProjectDto
import be.ducobu.inTime.exception.*
import be.ducobu.inTime.model.Client
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.service.ClientService
import be.ducobu.inTime.service.WorkspaceService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("client")
@Validated
class ClientRestController {

    Logger logger = LoggerFactory.getLogger(ClientRestController.class)

    private final ClientService clientService
    private final WorkspaceService workspaceService
    private final ModelMapper modelMapper

    ClientRestController(ClientService clientService, WorkspaceService workspaceService, ModelMapper modelMapper) {
        this.clientService = clientService
        this.workspaceService = workspaceService
        this.modelMapper = modelMapper
    }

    @GetMapping()
    List<ClientDto> getAll() {
        List<Client> clients = clientService.findAll()

        if (clients.isEmpty()) {
            throw new NoEntryFoundException("Client")
        }

        return modelMapper.map(clientService.findAll(),
                ClientDto[].class
        )
    }

    @GetMapping("/{id}")
    ClientDto getById(@PathVariable Long id) {
        return modelMapper.map(clientService.findById(id),
                ClientDto.class
        )
    }

    @GetMapping("/{id}/projects")
    List<ProjectDto> getProjectsById(@PathVariable Long id) {
        Client client = clientService.findById(id)
        return modelMapper.map(client.getProjects(),
                ProjectDto[].class
        )
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    ClientDto create(@RequestBody ClientCreateDto clientCreateDto) {
        String clientName = clientCreateDto.name
        String workspaceName = clientCreateDto.workspaceName

        if (null == clientName)
            throw new MissingNameException("Client")

        if (null == workspaceName)
            throw new MissingNameException("Client", "workspaceName")

        try {
            if (null != clientService.findByName(clientName))
                throw new DuplicateEntryException("Client", "name", clientName)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Client' found with this name, we can create it."
        }

        Workspace workspace = workspaceService.findByName(workspaceName)

        Client createdClient = clientService.save(
                modelMapper.map(
                        new ClientSaveDto(clientName, workspace),
                        Client.class
                )
        )

        return modelMapper.map(
                createdClient,
                ClientDto.class
        )
    }

    @PutMapping("/{id}")
    ClientDto update(@PathVariable Long id, @RequestBody ClientCreateDto clientCreateDto) {
        Client client = clientService.findById(id)
        Client unmodifiedClient = new Client(client)

        if (clientCreateDto.isEmpty())
            throw new NotModifiedEntityException("Client", id as String, "Nothing was sent in the body.")

        if (null != clientCreateDto.name)
            client.name = clientCreateDto.name

        if (null != clientCreateDto.workspaceName)
            client.workspace = workspaceService.findByName(clientCreateDto.workspaceName)

        // Check if any change were made to the Client
        if (client == unmodifiedClient)
            throw new NotModifiedEntityException("Client", id as String)

        return modelMapper.map(
                clientService.save(client),
                ClientDto.class
        )
    }

    @DeleteMapping("/{id}")
    ClientDto deleteClient(@PathVariable Long id) {
        Client client = clientService.findById(id)

        if (!client.getProjects().isEmpty())
            throw new ExistingChildFoundException("Project")

        clientService.deleteById(id)

        return modelMapper.map(
                client,
                ClientDto.class
        )
    }

    @DeleteMapping("/{id}/force")
    ClientDto forceDeleteClient(@PathVariable Long id) {
        Client client = clientService.findById(id)

        clientService.deleteById(id)

        return modelMapper.map(
                client,
                ClientDto.class
        )
    }
}

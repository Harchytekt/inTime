package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.client.ClientDto
import be.ducobu.inTime.dto.client.ClientCreateDto
import be.ducobu.inTime.dto.client.ClientSaveDto
import be.ducobu.inTime.dto.project.ProjectSaveDto
import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.exception.DuplicateEntryException
import be.ducobu.inTime.model.Client
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.service.ClientService
import be.ducobu.inTime.service.WorkspaceService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    @GetMapping("/{id}")
    ClientDto getById(@PathVariable Long id) {
        return modelMapper.map(clientService.findById(id),
                ClientDto.class
        )
    }

    @PostMapping("/")
    Long create(@RequestBody ClientCreateDto clientCreateDto) {
        Workspace workspace = workspaceService.findByName(clientCreateDto.getWorkspaceName())

        String clientName = clientCreateDto.getName()

        try {
            if (clientService.findByName(clientName) != null)
                throw new DuplicateEntryException("Client", "name", clientName)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Client' found with this name, we can create it."
        }

        ClientSaveDto clientSaveDto = new ClientSaveDto(
                clientName,
                workspace.getId()
        )

        return clientService.save(modelMapper.map(
                clientSaveDto,
                Client.class
        ))
    }
}

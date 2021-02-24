package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.client.ClientDto
import be.ducobu.inTime.dto.workspace.WorkspaceCreateDto
import be.ducobu.inTime.dto.workspace.WorkspaceDto
import be.ducobu.inTime.exception.*
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.service.WorkspaceService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("workspace")
@Validated
class WorkspaceRestController {

    Logger logger = LoggerFactory.getLogger(WorkspaceRestController.class)

    private final WorkspaceService workspaceService
    private final ModelMapper modelMapper

    WorkspaceRestController(WorkspaceService workspaceService, ModelMapper modelMapper) {
        this.workspaceService = workspaceService
        this.modelMapper = modelMapper
    }

    @GetMapping()
    List<WorkspaceDto> getAll() {
        List<Workspace> workspaces = workspaceService.findAll()

        if (workspaces.isEmpty()) {
            throw new NoEntryFoundException("Workspace")
        }

        return modelMapper.map(workspaceService.findAll(),
                WorkspaceDto[].class
        )
    }

    @GetMapping("/{id}")
    WorkspaceDto getById(@PathVariable Long id) {
        return modelMapper.map(workspaceService.findById(id),
                WorkspaceDto.class
        )
    }

    @GetMapping("/{id}/clients")
    List<ClientDto> getClientsById(@PathVariable Long id) {
        Workspace workspace = workspaceService.findById(id)
        return modelMapper.map(workspace.getClients(),
                ClientDto[].class
        )
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    WorkspaceDto create(@RequestBody WorkspaceCreateDto workspaceCreateDto) {

        String workspaceName = workspaceCreateDto.name

        try {
            if (workspaceService.findByName(workspaceName) != null)
                throw new DuplicateEntryException("Workspace", "name", workspaceName)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Workspace' found with this name, we can create it."
        }

        Workspace createdWorkspace = workspaceService.save(
                modelMapper.map(
                        workspaceCreateDto,
                        Workspace.class
                )
        )

        return modelMapper.map(
                createdWorkspace,
                WorkspaceDto.class
        )
    }

    @PutMapping("/{id}")
    WorkspaceDto update(@PathVariable Long id, @RequestBody WorkspaceCreateDto workspaceCreateDto) {
        Workspace workspace = workspaceService.findById(id)

        if (workspaceCreateDto.name == null && workspaceCreateDto.togglId == null) {
            logger.info "The entity 'Workspace' with attribute '$id' couldn't be updated! Nothing was sent in the body."
            throw new NotModifiedEntityException("Workspace", id as String, "Nothing was sent in the body.")
        }
        // TODO: add check if name and togglId values are the same as the current ones

        if (workspaceCreateDto.name != null)
            workspace.name = workspaceCreateDto.name

        if (workspaceCreateDto.togglId != null)
            workspace.togglId = workspaceCreateDto.togglId

        return modelMapper.map(
                workspaceService.save(workspace),
                WorkspaceDto.class
        )
    }

    @DeleteMapping("/{id}")
    WorkspaceDto deleteWorkspace(@PathVariable Long id) {
        Workspace workspace = workspaceService.findById(id)

        if (!workspace.getClients().isEmpty())
            throw new ExistingChildFoundException("Client")

        workspaceService.deleteById(id)

        return modelMapper.map(
                workspace,
                WorkspaceDto.class
        )
    }

    @DeleteMapping("/{id}/force")
    WorkspaceDto forceDeleteWorkspace(@PathVariable Long id) {
        Workspace workspace = workspaceService.findById(id)

        workspaceService.deleteById(id)

        return modelMapper.map(
                workspace,
                WorkspaceDto.class
        )
    }
}

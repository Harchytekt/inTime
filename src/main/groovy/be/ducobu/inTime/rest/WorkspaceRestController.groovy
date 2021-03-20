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
        if (workspaceCreateDto.togglId == 0) workspaceCreateDto.togglId = null
        Long workspaceTogglId = workspaceCreateDto.togglId

        if (workspaceName == null)
            throw new MissingNameException("Workspace")

        try {
            if (workspaceService.findByName(workspaceName) != null)
                throw new DuplicateEntryException("Workspace", "name", workspaceName)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Workspace' found with this name, we can create it."
        }

        try {
            if (workspaceTogglId != null && workspaceService.findByTogglId(workspaceTogglId) != null)
                throw new DuplicateEntryException("Workspace", "togglId", workspaceTogglId as String)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Workspace' found with this togglId, we can create it."
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
        Workspace unmodifiedWorkspace = new Workspace(workspace)

        if (workspaceCreateDto.isEmpty())
            throw new NotModifiedEntityException("Workspace", id as String, "Nothing was sent in the body.")

        if (workspaceCreateDto.name != null)
            workspace.name = workspaceCreateDto.name

        if (workspaceCreateDto.togglId != null && workspaceCreateDto.togglId != 0)
            workspace.togglId = workspaceCreateDto.togglId

        // Check if any change were made to the Workspace
        if (workspace == unmodifiedWorkspace)
            throw new NotModifiedEntityException("Workspace", id as String)

        return modelMapper.map(
                workspaceService.save(workspace),
                WorkspaceDto.class
        )
    }

    @PutMapping("/{id}/togglid")
    WorkspaceDto deleteWorkspaceTogglID(@PathVariable Long id) {
        Workspace workspace = workspaceService.findById(id)

        if (workspace.togglId == null)
            throw new TogglIdAlreadyNullException("Workspace", id)

        workspace.togglId = null

        return modelMapper.map(
                workspace,
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

package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.client.ClientCreateDto
import be.ducobu.inTime.dto.client.ClientDto
import be.ducobu.inTime.dto.workspace.WorkspaceCreateDto
import be.ducobu.inTime.dto.workspace.WorkspaceDto
import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.exception.DuplicateEntryException
import be.ducobu.inTime.model.Client
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.service.WorkspaceService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    @GetMapping("/{id}")
    WorkspaceDto getById(@PathVariable Long id) {
        return modelMapper.map(workspaceService.findById(id),
                WorkspaceDto.class
        )
    }

    @PostMapping("/")
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

        if (workspaceCreateDto.name != null)
            workspace.name = workspaceCreateDto.name

        return modelMapper.map(
                workspaceService.save(workspace),
                WorkspaceDto.class
        )
    }
}

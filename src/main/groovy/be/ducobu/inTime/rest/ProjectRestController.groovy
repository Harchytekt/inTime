package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.project.ProjectCreateDto
import be.ducobu.inTime.dto.project.ProjectDto
import be.ducobu.inTime.dto.project.ProjectSaveDto
import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.exception.DuplicateEntryException
import be.ducobu.inTime.model.Client
import be.ducobu.inTime.model.Project
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.service.ClientService
import be.ducobu.inTime.service.ProjectService
import be.ducobu.inTime.service.WorkspaceService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("project")
@Validated
class ProjectRestController {

    Logger logger = LoggerFactory.getLogger(ProjectRestController.class)

    private final ClientService clientService
    private final ProjectService projectService
    private final WorkspaceService workspaceService
    private final ModelMapper modelMapper

    ProjectRestController(ClientService clientService, ProjectService projectService, WorkspaceService workspaceService, ModelMapper modelMapper) {
        this.clientService = clientService
        this.projectService = projectService
        this.workspaceService = workspaceService
        this.modelMapper = modelMapper
    }

    @GetMapping("/{id}")
    ProjectDto getById(@PathVariable Long id) {
        return modelMapper.map(projectService.findById(id),
                ProjectDto.class
        )
    }

    @PostMapping("/")
    Long create(@RequestBody ProjectCreateDto projectCreateDto) {
        Workspace workspace = workspaceService.findByName(projectCreateDto.getWorkspaceName())
        Client client = clientService.findByName(projectCreateDto.getClientName())

        String projectName = projectCreateDto.getName()

        try {
            if (projectService.findByName(projectName) != null)
                throw new DuplicateEntryException("Project", "name", projectName)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Project' found with this name, we can create it."
        }

        ProjectSaveDto projectSaveDto = new ProjectSaveDto(
                projectName,
                new Boolean(projectCreateDto.getBillable()),
                workspace.getId(),
                client.getId()
        )

        return projectService.save(modelMapper.map(
                projectSaveDto,
                Project.class
        ))
    }

    @PutMapping("/{id}")
    Long update(@PathVariable Long id, @RequestBody ProjectCreateDto projectCreateDto) {
        Project project = projectService.findById(id)

        if (projectCreateDto.getName() != null) {
            project.setName(projectCreateDto.getName())
        }
        if (projectCreateDto.getBillable() != null) {
            project.setBillable(projectCreateDto.getBillable())
        }
        if (projectCreateDto.getTogglId() != null) {
            project.setTogglId(projectCreateDto.getTogglId())
        }
        if (projectCreateDto.getWorkspaceName() != null) {
            Workspace workspace = workspaceService.findByName(projectCreateDto.getWorkspaceName())
            project.setWorkspace(workspace)
        }
        if (projectCreateDto.getClientName() != null) {
            Client client = clientService.findByName(projectCreateDto.getClientName())
            project.setClient(client)
        }

        return projectService.save(project)
    }

}

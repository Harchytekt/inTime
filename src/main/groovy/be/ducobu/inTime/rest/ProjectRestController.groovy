package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.project.ProjectCreateDto
import be.ducobu.inTime.dto.project.ProjectDto
import be.ducobu.inTime.dto.project.ProjectSaveDto
import be.ducobu.inTime.dto.timeEntry.TimeEntryDto
import be.ducobu.inTime.exception.*
import be.ducobu.inTime.model.Client
import be.ducobu.inTime.model.Project
import be.ducobu.inTime.service.ClientService
import be.ducobu.inTime.service.ProjectService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("project")
@Validated
class ProjectRestController {

    Logger logger = LoggerFactory.getLogger(ProjectRestController.class)

    private final ClientService clientService
    private final ProjectService projectService
    private final ModelMapper modelMapper

    ProjectRestController(ClientService clientService, ProjectService projectService, ModelMapper modelMapper) {
        this.clientService = clientService
        this.projectService = projectService
        this.modelMapper = modelMapper
    }

    @GetMapping()
    List<ProjectDto> getAll() {
        List<Project> projects = projectService.findAll()

        if (projects.isEmpty()) {
            throw new NoEntryFoundException("Project")
        }

        return modelMapper.map(projectService.findAll(),
                ProjectDto[].class
        )
    }

    @GetMapping("/{id}")
    ProjectDto getById(@PathVariable Long id) {
        return modelMapper.map(projectService.findById(id),
                ProjectDto.class
        )
    }

    @GetMapping("/{id}/timeentries")
    List<TimeEntryDto> getTimeEntriesById(@PathVariable Long id) {
        Project project = projectService.findById(id)
        return modelMapper.map(project.getTimeEntries(),
                TimeEntryDto[].class
        )
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    ProjectDto create(@RequestBody ProjectCreateDto projectCreateDto) {
        String projectName = projectCreateDto.name
        String clientName = projectCreateDto.clientName

        if (projectName == null)
            throw new MissingNameException("Project")

        if (clientName == null)
            throw new MissingNameException("Project", "clientName")

        try {
            if (projectService.findByName(projectName) != null)
                throw new DuplicateEntryException("Project", "name", projectName)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'Project' found with this name, we can create it."
        }

        Client client = clientService.findByName(clientName)

        ProjectSaveDto projectSaveDto = new ProjectSaveDto(
                projectName,
                client
        )

        Project createdProject = projectService.save(
                modelMapper.map(
                        projectSaveDto,
                        Project.class
                )
        )

        return modelMapper.map(
                createdProject,
                ProjectDto.class
        )
    }

    @PutMapping("/{id}")
    ProjectDto update(@PathVariable Long id, @RequestBody ProjectCreateDto projectCreateDto) {
        Project project = projectService.findById(id)
        Project unmodifiedProject = new Project(project)

        if (projectCreateDto.isEmpty())
            throw new NotModifiedEntityException("Project", id as String, "Nothing was sent in the body.")

        if (projectCreateDto.name != null)
            project.name = projectCreateDto.name

        if (projectCreateDto.clientName != null)
            project.client = clientService.findByName(projectCreateDto.clientName)

        // Check if any change were made to the Project
        if (project == unmodifiedProject)
            throw new NotModifiedEntityException("Project", id as String)

        return modelMapper.map(
                projectService.save(project),
                ProjectDto.class
        )
    }

    @DeleteMapping("/{id}")
    ProjectDto deleteProject(@PathVariable Long id) {
        Project project = projectService.findById(id)

        if (!project.getTimeEntries().isEmpty())
            throw new ExistingChildFoundException("TimeEntry")

        projectService.deleteById(id)

        return modelMapper.map(
                project,
                ProjectDto.class
        )
    }

    @DeleteMapping("/{id}/force")
    ProjectDto forceDeleteProject(@PathVariable Long id) {
        Project project = projectService.findById(id)

        projectService.deleteById(id)

        return modelMapper.map(
                project,
                ProjectDto.class
        )
    }

}

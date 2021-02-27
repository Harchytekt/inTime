package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.project.ProjectCreateDto
import be.ducobu.inTime.dto.project.ProjectDto
import be.ducobu.inTime.dto.project.ProjectSaveDto
import be.ducobu.inTime.dto.timeEntry.TimeEntryDto
import be.ducobu.inTime.exception.DuplicateEntryException
import be.ducobu.inTime.exception.ExistingChildFoundException
import be.ducobu.inTime.exception.NoEntryFoundException
import be.ducobu.inTime.exception.NotModifiedEntityException
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
        Client client = clientService.findByName(projectCreateDto.clientName)

        String projectName = projectCreateDto.name

        if (projectService.findByName(projectName) != null)
            throw new DuplicateEntryException("Project", "name", projectName)

        ProjectSaveDto projectSaveDto = new ProjectSaveDto(
                projectName,
                new Boolean(projectCreateDto.billable),
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

        if (projectCreateDto.billable != null)
            project.billable = projectCreateDto.billable

        if (projectCreateDto.togglId != null)
            project.togglId = projectCreateDto.togglId

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

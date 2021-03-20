package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.timeEntry.*
import be.ducobu.inTime.exception.*
import be.ducobu.inTime.model.Project
import be.ducobu.inTime.model.TimeEntry
import be.ducobu.inTime.service.ProjectService
import be.ducobu.inTime.service.TimeEntryService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import java.time.Duration
import java.time.LocalDateTime

@RestController
@RequestMapping("time_entry")
@Validated
class TimeEntryRestController {

    Logger logger = LoggerFactory.getLogger(TimeEntryRestController.class)

    private final ProjectService projectService
    private final TimeEntryService timeEntryService
    private final ModelMapper modelMapper

    TimeEntryRestController(ProjectService projectService, TimeEntryService timeEntryService, ModelMapper modelMapper) {
        this.projectService = projectService
        this.timeEntryService = timeEntryService
        this.modelMapper = modelMapper
    }

    @GetMapping()
    List<TimeEntryDto> getAll() {
        List<TimeEntry> timeEntry = timeEntryService.findAll()

        if (timeEntry.isEmpty()) {
            throw new NoEntryFoundException("TimeEntry")
        }

        return modelMapper.map(timeEntryService.findAll(),
                TimeEntryDto[].class
        )
    }

    @GetMapping("/{id}")
    TimeEntryDto getById(@PathVariable Long id) {
        return modelMapper.map(
                timeEntryService.findById(id),
                TimeEntryDto.class
        )
    }

    @GetMapping("/{id}/duration")
    TimeEntryDurationDto getDurationById(@PathVariable Long id) {
        return modelMapper.map(
                timeEntryService.findById(id),
                TimeEntryDurationDto.class
        )
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    TimeEntryDto create(@RequestBody TimeEntryCreateDto timeEntryCreateDto) {
        LocalDateTime date = LocalDateTime.now()
        Long timeEntryTogglId = timeEntryCreateDto.togglId

        if (timeEntryCreateDto.projectName == null)
            throw new MissingNameException("TimeEntry", "projectName")

        try {
            Long stoppedTimeEntryId = this.stopTimeEntry().id
            date = timeEntryService.findById(stoppedTimeEntryId).endDate
        } catch (RunningTimeEntryNotFoundException ignored) {
            logger.info "No running 'TimeEntry' found, we don't have to stop it then."
        }

        try {
            if (timeEntryService.findByTogglId(timeEntryTogglId) != null)
                throw new DuplicateEntryException("TimeEntry", "togglId", timeEntryTogglId as String)
        } catch (CustomEntityNotFoundException ignored) {
            logger.info "No 'TimeEntry' found with this togglId, we can create it."
        }

        Project project = projectService.findByName(timeEntryCreateDto.projectName)
        TimeEntry createdTimeEntry = timeEntryService.save(
                modelMapper.map(
                        new TimeEntrySaveDto(project, date, timeEntryCreateDto.description),
                        TimeEntry.class
                )
        )

        return modelMapper.map(
                createdTimeEntry,
                TimeEntryDto.class
        )
    }

    @PutMapping("/{id}")
    TimeEntryDto update(@PathVariable Long id, @RequestBody TimeEntryUpdateDto timeEntryUpdateDto) {
        TimeEntry timeEntry = timeEntryService.findById(id)
        TimeEntry unmodifiedTimeEntry = new TimeEntry(timeEntry)

        if (timeEntryUpdateDto.isEmpty())
            throw new NotModifiedEntityException("TimeEntry", id as String, "Nothing was sent in the body.")

        if (timeEntryUpdateDto.togglId != null)
            timeEntry.togglId = timeEntryUpdateDto.togglId

        if (timeEntryUpdateDto.startDate != null)
            timeEntry.startDate = timeEntryUpdateDto.startDate

        if (timeEntryUpdateDto.endDate != null) {
            if (Duration.between(timeEntry.startDate, timeEntryUpdateDto.endDate).getSeconds() > 0) {
                timeEntry.updateEndDate(timeEntryUpdateDto.endDate)
            } else {
                throw new EndDateExceededException(timeEntry.startDate, timeEntryUpdateDto.endDate)
            }
        }

        if (timeEntryUpdateDto.description != null)
            timeEntry.description = timeEntryUpdateDto.description

        if (timeEntryUpdateDto.projectName != null)
            timeEntry.project = projectService.findByName(timeEntryUpdateDto.projectName)

        // Check if any change were made to the Workspace
        if (timeEntry == unmodifiedTimeEntry)
            throw new NotModifiedEntityException("TimeEntry", id as String)

        return modelMapper.map(
                timeEntryService.save(timeEntry),
                TimeEntryDto.class
        )
    }

    @PutMapping("/stop")
    TimeEntryDto stopTimeEntry() {
        TimeEntry timeEntry = timeEntryService.findRunningTimeEntry()
        if (timeEntry == null)
            throw new RunningTimeEntryNotFoundException()

        timeEntry.stop()

        return modelMapper.map(
                timeEntryService.save(timeEntry),
                TimeEntryDto.class
        )
    }

    @PutMapping("/restart")
    TimeEntryDto restartTimeEntry() {
        TimeEntry timeEntry = timeEntryService.findLastTimeEntry()
        if (timeEntry.running)
            throw new RunningTimeEntryException("A 'TimeEntry' is already running!")

        timeEntry.restart()

        return modelMapper.map(
                timeEntryService.save(timeEntry),
                TimeEntryDto.class
        )
    }

    @PutMapping("/{id}/togglid")
    TimeEntryDto deleteTimeEntryTogglID(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryService.findById(id)

        if (timeEntry.togglId == null)
            throw new TogglIdAlreadyNullException("TimeEntry", id)

        timeEntry.togglId = null

        return modelMapper.map(
                timeEntry,
                TimeEntryDto.class
        )
    }

    @DeleteMapping("/{id}")
    TimeEntryDto deleteTimeEntry(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryService.findById(id)

        if (timeEntry.running)
            throw new RunningTimeEntryException("The 'TimeEntry' is still running!")

        timeEntryService.deleteById(id)

        return modelMapper.map(
                timeEntry,
                TimeEntryDto.class
        )
    }

    @DeleteMapping("/{id}/force")
    TimeEntryDto forceDeleteTimeEntry(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryService.findById(id)

        timeEntryService.deleteById(id)

        return modelMapper.map(
                timeEntry,
                TimeEntryDto.class
        )
    }
}

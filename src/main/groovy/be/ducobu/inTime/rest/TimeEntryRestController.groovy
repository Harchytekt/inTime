package be.ducobu.inTime.rest


import be.ducobu.inTime.dto.timeEntry.*
import be.ducobu.inTime.exception.NoEntryFoundException
import be.ducobu.inTime.exception.RunningTimeEntryException
import be.ducobu.inTime.exception.RunningTimeEntryNotFoundException
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

        try {
            Long stoppedTimeEntryId = this.stopTimeEntry().id
            date = timeEntryService.findById(stoppedTimeEntryId).endDate
        } catch (RunningTimeEntryNotFoundException ignored) {
            logger.info "No running 'TimeEntry' found, we don't have to stop it then."
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

        if (timeEntryUpdateDto.togglId != null)
            timeEntry.togglId = timeEntryUpdateDto.togglId

        if (timeEntryUpdateDto.startDate != null)
            timeEntry.startDate = timeEntryUpdateDto.startDate

        if (timeEntryUpdateDto.endDate != null && !timeEntry.running) {
            timeEntry.updateEndDate(timeEntryUpdateDto.endDate)
        } else if (timeEntryUpdateDto.endDate != null && timeEntry.running) {
            throw new RunningTimeEntryException("The 'TimeEntry' is still running!")
        }

        if (timeEntryUpdateDto.description != null)
            timeEntry.description = timeEntryUpdateDto.description

        if (timeEntryUpdateDto.projectName != null)
            timeEntry.project = projectService.findByName(timeEntryUpdateDto.projectName)

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

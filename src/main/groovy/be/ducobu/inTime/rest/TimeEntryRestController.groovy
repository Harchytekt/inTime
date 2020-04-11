package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.timeEntry.TimeEntryCreateDto
import be.ducobu.inTime.dto.timeEntry.TimeEntryDto
import be.ducobu.inTime.dto.timeEntry.TimeEntrySaveDto
import be.ducobu.inTime.model.Project
import be.ducobu.inTime.model.TimeEntry
import be.ducobu.inTime.service.ProjectService
import be.ducobu.inTime.service.TimeEntryService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    @GetMapping("/{id}")
    TimeEntryDto getById(@PathVariable Long id) {
        return modelMapper.map(timeEntryService.findById(id),
                TimeEntryDto.class
        )
    }

    @GetMapping("/{id}/duration")
    Long getDurationById(@PathVariable Long id) {
        return timeEntryService.findById(id).duration
    }

    @PostMapping("/")
    Long create(@RequestBody TimeEntryCreateDto timeEntryCreateDto) {
        LocalDateTime date = LocalDateTime.now()

        Long stoppedTimeEntryId = this.stopTimeEntry()
        if (stoppedTimeEntryId != -1) {
            date = timeEntryService.findById(stoppedTimeEntryId).getEndDate()
        }

        Project project = projectService.findByName(timeEntryCreateDto.getProjectName())
        TimeEntrySaveDto timeEntrySaveDto = new TimeEntrySaveDto(project.getId(), date, timeEntryCreateDto.getDescription())

        return timeEntryService.save(modelMapper.map(
                timeEntrySaveDto,
                TimeEntry.class
        ))
    }

    @PutMapping("/")
    Long stopTimeEntry() {
        TimeEntry timeEntry = timeEntryService.findRunningTimeEntry()
        if (timeEntry == null)
            return -1

        timeEntry.stop()
        return timeEntryService.save(timeEntry)
    }
}

package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.timeEntry.*
import be.ducobu.inTime.exception.RunningTimeEntryException
import be.ducobu.inTime.exception.RunningTimeEntryNotFoundException
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
    TimeEntryDto create(@RequestBody TimeEntryCreateDto timeEntryCreateDto) {
        LocalDateTime date = LocalDateTime.now()

        try {
            Long stoppedTimeEntryId = this.stopTimeEntry().getId()
            date = timeEntryService.findById(stoppedTimeEntryId).getEndDate()
        } catch (RunningTimeEntryNotFoundException ignored) {
            logger.info "No running 'Time Entry' found, we don't have to stop it then."
        }

        Project project = projectService.findByName(timeEntryCreateDto.getProjectName())
        TimeEntry createdTimeEntry = timeEntryService.save(
                modelMapper.map(
                        new TimeEntrySaveDto(project, date, timeEntryCreateDto.getDescription()),
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

        if (timeEntryUpdateDto.getTogglId() != null) {
            timeEntry.setTogglId(timeEntryUpdateDto.getTogglId())
        }
        if (timeEntryUpdateDto.getStartDate() != null) {
            timeEntry.setStartDate(timeEntryUpdateDto.getStartDate())
        }
        if (timeEntryUpdateDto.getEndDate() != null && !timeEntry.getRunning()) {
            timeEntry.setEndDate(timeEntryUpdateDto.getEndDate())
            timeEntry.setDuration(timeEntry.calculateDuration())
        } else if (timeEntryUpdateDto.getEndDate() != null && timeEntry.getRunning()) {
            throw new RunningTimeEntryException("The 'Time Entry' is still running!")
        }
        if (timeEntryUpdateDto.getDescription() != null) {
            timeEntry.setDescription(timeEntryUpdateDto.getDescription())
        }
        if (timeEntryUpdateDto.getProjectName() != null) {
            Project project = projectService.findByName(timeEntryUpdateDto.getProjectName())
            timeEntry.setProject(project)
        }

        return modelMapper.map(
                timeEntryService.save(timeEntry),
                TimeEntryDto.class
        )
    }

    @PutMapping("/")
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

    @PutMapping("/restart/")
    TimeEntryDto restartTimeEntry() {
        TimeEntry timeEntry = timeEntryService.findLastTimeEntry()
        if (timeEntry.getRunning())
            throw new RunningTimeEntryException("A 'Time Entry' is already running!")

        timeEntry.restart()

        return modelMapper.map(
                timeEntryService.save(timeEntry),
                TimeEntryDto.class
        )
    }
}

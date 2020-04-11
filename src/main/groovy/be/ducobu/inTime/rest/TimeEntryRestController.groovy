package be.ducobu.inTime.rest

import be.ducobu.inTime.dto.TimeEntryDto
import be.ducobu.inTime.service.TimeEntryService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("time_entry")
@Validated
class TimeEntryRestController {

    Logger logger = LoggerFactory.getLogger(TimeEntryRestController.class)

    private final TimeEntryService timeEntryService
    private final ModelMapper modelMapper

    TimeEntryRestController(TimeEntryService timeEntryService, ModelMapper modelMapper) {
        this.timeEntryService = timeEntryService
        this.modelMapper = modelMapper
    }

    @GetMapping("/{id}")
    TimeEntryDto getById(@PathVariable Long id) {
        return modelMapper.map(timeEntryService.findById(id),
                TimeEntryDto.class
        )
    }
}

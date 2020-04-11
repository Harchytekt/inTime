package be.ducobu.inTime.service

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.model.TimeEntry
import be.ducobu.inTime.repository.TimeEntryRepository
import org.springframework.stereotype.Service

@Service
class TimeEntryService {

    private final TimeEntryRepository timeEntryRepository

    TimeEntryService(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository
    }

    TimeEntry findById(Long id) {
        return timeEntryRepository.findById(id)
                .orElseThrow({ -> new CustomEntityNotFoundException("TimeEntry", id) })
    }

}
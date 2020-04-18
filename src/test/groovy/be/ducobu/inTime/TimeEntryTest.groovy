package be.ducobu.inTime

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.model.TimeEntry
import be.ducobu.inTime.service.ProjectService
import be.ducobu.inTime.service.TimeEntryService
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import java.time.LocalDateTime

import static org.junit.jupiter.api.Assertions.*
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TimeEntryTest {

    @Autowired
    private TimeEntryService timeEntryService

    @Autowired
    private ProjectService projectService

    @Test
    @Order(1)
    void whenFindById_thenReturnTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(1L)

        // then
        assertEquals 1L, found.id
        assertNull found.togglId
        assertEquals LocalDateTime.of(2020, 04, 17, 14, 28, 42), found.startDate
        assertNull found.endDate
        assertNotNull found.duration.getClass()
        assertEquals Long, found.duration.getClass()
        assertNull found.description
        assertTrue found.running
        assertNotNull found.project
        assertEquals 1L, found.project.id
        assertEquals "First Project", found.project.name
    }

    @Test
    @Order(2)
    void whenFindByWrongId_thenReturnException() {

        Throwable exception = assertThrows CustomEntityNotFoundException.class, { ->
            timeEntryService.findById(2L)
        }
        assertEquals "No 'TimeEntry' with attribute '2' found!", exception.getMessage()
    }

    @Test
    @Order(3)
    void whenSaveTimeEntry_thenReturnTimeEntry() {

        // when
        TimeEntry timeEntry = new TimeEntry()
        LocalDateTime dateTime = LocalDateTime.of(2020, 04, 17, 14, 30, 00)

        timeEntry.project = projectService.findByName("Second Project")
        timeEntry.description = "Test"
        timeEntry.startDate = dateTime
        TimeEntry savedTimeEntry = timeEntryService.save(timeEntry)

        // then
        assertNotNull savedTimeEntry
        assertEquals 2L, savedTimeEntry.id
        assertNull savedTimeEntry.togglId
        assertEquals dateTime, savedTimeEntry.startDate
        assertNull savedTimeEntry.endDate
        assertEquals Long, savedTimeEntry.duration.getClass()
        assertNotNull savedTimeEntry.description
        assertEquals "Test", savedTimeEntry.description
        assertTrue savedTimeEntry.running
        assertNotNull savedTimeEntry.project
        assertEquals 2L, savedTimeEntry.project.id
        assertEquals "Second Project", savedTimeEntry.project.name
    }

    @Test
    @Order(4)
    void whenStopTimeEntry_thenReturnStoppedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assertNotNull found
        found.stop()
        TimeEntry stoppedTimeEntry = timeEntryService.save(found)

        // then
        assertNotNull stoppedTimeEntry
        assertEquals 2L, stoppedTimeEntry.id
        assertNotNull stoppedTimeEntry.endDate
        assertEquals Long, stoppedTimeEntry.duration.getClass()
        assertFalse stoppedTimeEntry.running
    }

    @Test
    @Order(5)
    void whenRestartTimeEntry_thenReturnRestartedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assertNotNull found
        found.restart()
        TimeEntry restartedTimeEntry = timeEntryService.save(found)

        // then
        assertNotNull restartedTimeEntry
        assertEquals 2L, restartedTimeEntry.id
        assertNull restartedTimeEntry.endDate
        assertEquals Long, restartedTimeEntry.duration.getClass()
        assertTrue restartedTimeEntry.running
    }

    @Test
    @Order(6)
    void whenUpdateTimeEntry_thenReturnUpdatedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assertNotNull found
        found.togglId = 12L
        found.project = projectService.findByName("First Project")
        LocalDateTime dateTime = LocalDateTime.of(2020, 04, 17, 16, 42, 00)
        found.startDate = dateTime
        found.description = "Test with update"
        TimeEntry updatedTimeEntry = timeEntryService.save(found)

        // then
        assertNotNull updatedTimeEntry
        assertEquals 2L, updatedTimeEntry.id
        assertEquals 12L, updatedTimeEntry.togglId
        assertEquals dateTime, updatedTimeEntry.startDate
        assertNull updatedTimeEntry.endDate
        assertNotNull updatedTimeEntry.description
        assertEquals "Test with update", updatedTimeEntry.description
        assertTrue updatedTimeEntry.running
        assertNotNull updatedTimeEntry.project
        assertEquals 1L, updatedTimeEntry.project.id
        assertEquals "First Project", updatedTimeEntry.project.name
    }

}

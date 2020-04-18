package be.ducobu.inTime

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.exception.RunningTimeEntryNotFoundException
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
        assertEquals 1L, found.getId()
        assertNull found.getTogglId()
        assertEquals LocalDateTime.of(2020, 04, 17, 14, 28, 42), found.getStartDate()
        assertNull found.getEndDate()
        assertNotNull found.getDuration().getClass()
        assertEquals Long, found.getDuration().getClass()
        assertNull found.getDescription()
        assertTrue found.getRunning()
        assertNotNull found.getProject()
        assertEquals 1L, found.getProject().getId()
        assertEquals "First Project", found.getProject().getName()
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

        timeEntry.setProject projectService.findByName("Second Project")
        timeEntry.setDescription "Test"
        timeEntry.setStartDate dateTime
        TimeEntry savedTimeEntry = timeEntryService.save(timeEntry)

        // then
        assertNotNull savedTimeEntry
        assertEquals 2L, savedTimeEntry.getId()
        assertNull savedTimeEntry.getTogglId()
        assertEquals dateTime, savedTimeEntry.getStartDate()
        assertNull savedTimeEntry.getEndDate()
        assertEquals Long, savedTimeEntry.getDuration().getClass()
        assertNotNull savedTimeEntry.getDescription()
        assertEquals "Test", savedTimeEntry.getDescription()
        assertTrue savedTimeEntry.getRunning()
        assertNotNull savedTimeEntry.getProject()
        assertEquals 2L, savedTimeEntry.getProject().getId()
        assertEquals "Second Project", savedTimeEntry.getProject().getName()
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
        assertEquals 2L, stoppedTimeEntry.getId()
        assertNotNull stoppedTimeEntry.getEndDate()
        assertEquals Long, stoppedTimeEntry.getDuration().getClass()
        assertFalse stoppedTimeEntry.getRunning()
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
        assertEquals 2L, restartedTimeEntry.getId()
        assertNull restartedTimeEntry.getEndDate()
        assertEquals Long, restartedTimeEntry.getDuration().getClass()
        assertTrue restartedTimeEntry.getRunning()
    }

    @Test
    @Order(6)
    void whenUpdateTimeEntry_thenReturnUpdatedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assertNotNull found
        found.setTogglId 12L
        found.setProject projectService.findByName("First Project")
        LocalDateTime dateTime = LocalDateTime.of(2020, 04, 17, 16, 42, 00)
        found.setStartDate dateTime
        found.setDescription "Test with update"
        TimeEntry updatedTimeEntry = timeEntryService.save(found)

        // then
        assertNotNull updatedTimeEntry
        assertEquals 2L, updatedTimeEntry.getId()
        assertEquals 12L, updatedTimeEntry.getTogglId()
        assertEquals dateTime, updatedTimeEntry.getStartDate()
        assertNull updatedTimeEntry.getEndDate()
        assertNotNull updatedTimeEntry.getDescription()
        assertEquals "Test with update", updatedTimeEntry.getDescription()
        assertTrue updatedTimeEntry.getRunning()
        assertNotNull updatedTimeEntry.getProject()
        assertEquals 1L, updatedTimeEntry.getProject().getId()
        assertEquals "First Project", updatedTimeEntry.getProject().getName()
    }

}

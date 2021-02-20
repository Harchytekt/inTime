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

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TimeEntryTest extends GroovyTestCase {

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
        assert 1L == found.id
        assert null == found.togglId
        assert LocalDateTime.of(2021, 01, 01, 14, 28, 42) == found.startDate
        assert null == found.endDate
        assert null != found.duration.getClass()
        assert Long == found.duration.getClass()
        assert null == found.description
        assert found.running
        assert null != found.project
        assert 1L == found.project.id
        assert "My First Project" == found.project.name
    }

    @Test
    @Order(2)
    void whenFindByWrongId_thenReturnException() {

        def msg = shouldFail CustomEntityNotFoundException, {
            timeEntryService.findById(2L)
        }
        assert "No 'TimeEntry' with attribute '2' found!" == msg
    }

    @Test
    @Order(3)
    void whenSaveTimeEntry_thenReturnTimeEntry() {

        // when
        TimeEntry timeEntry = new TimeEntry()
        LocalDateTime dateTime = LocalDateTime.of(2021, 01, 01, 14, 30, 00)

        timeEntry.project = projectService.findByName("My Second Project")
        timeEntry.description = "Test"
        timeEntry.startDate = dateTime
        TimeEntry savedTimeEntry = timeEntryService.save(timeEntry)

        // then
        assert null != savedTimeEntry
        assert 2L == savedTimeEntry.id
        assert null == savedTimeEntry.togglId
        assert dateTime == savedTimeEntry.startDate
        assert null == savedTimeEntry.endDate
        assert Long == savedTimeEntry.duration.getClass()
        assert null != savedTimeEntry.description
        assert "Test" == savedTimeEntry.description
        assert savedTimeEntry.running
        assert null != savedTimeEntry.project
        assert 2L == savedTimeEntry.project.id
        assert "My Second Project" == savedTimeEntry.project.name
    }

    @Test
    @Order(4)
    void whenFindAll_thenReturnTimeEntries() {

        // when
        List<TimeEntry> found = timeEntryService.findAll()

        // then
        assert 2 == found.size()
        assert "My First Project" == found[0].project.name
        assert "My Second Project" == found[1].project.name
    }

    @Test
    @Order(5)
    void whenStopTimeEntry_thenReturnStoppedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assert null != found
        found.stop()
        TimeEntry stoppedTimeEntry = timeEntryService.save(found)

        // then
        assert null != stoppedTimeEntry
        assert 2L == stoppedTimeEntry.id
        assert null != stoppedTimeEntry.endDate
        assert Long == stoppedTimeEntry.duration.getClass()
        assert !stoppedTimeEntry.running
    }

    @Test
    @Order(6)
    void whenRestartTimeEntry_thenReturnRestartedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assert null != found
        found.restart()
        TimeEntry restartedTimeEntry = timeEntryService.save(found)

        // then
        assert null != restartedTimeEntry
        assert 2L == restartedTimeEntry.id
        assert null == restartedTimeEntry.endDate
        assert Long == restartedTimeEntry.duration.getClass()
        assert restartedTimeEntry.running
    }

    @Test
    @Order(7)
    void whenUpdateTimeEntry_thenReturnUpdatedTimeEntry() {

        // when
        TimeEntry found = timeEntryService.findById(2L)
        assert null != found
        found.togglId = 12L
        found.project = projectService.findByName("My First Project")
        LocalDateTime dateTime = LocalDateTime.of(2020, 04, 17, 16, 42, 00)
        found.startDate = dateTime
        found.description = "Test with update"
        TimeEntry updatedTimeEntry = timeEntryService.save(found)

        // then
        assert null != updatedTimeEntry
        assert 2L == updatedTimeEntry.id
        assert 12L == updatedTimeEntry.togglId
        assert dateTime == updatedTimeEntry.startDate
        assert null == updatedTimeEntry.endDate
        assert null != updatedTimeEntry.description
        assert "Test with update" == updatedTimeEntry.description
        assert updatedTimeEntry.running
        assert null != updatedTimeEntry.project
        assert 1L == updatedTimeEntry.project.id
        assert "My First Project" == updatedTimeEntry.project.name
    }

}

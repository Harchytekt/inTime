package be.ducobu.inTime

import be.ducobu.inTime.model.TimeEntry
import be.ducobu.inTime.service.ProjectService
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import java.time.LocalDateTime

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TimeEntryTest extends GroovyTestCase {

    @Autowired
    private MockMvc mvc

    @Autowired
    private ProjectService projectService

    @Test
    @Order(1)
    void whenGetTimeEntryById_thenReturnTimeEntry_withStatus200() throws Exception {

        mvc.perform(get("/time_entry/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.startDate', is("2021-01-01T14:28:42")))
                .andExpect(jsonPath('$.endDate', is("2021-01-01T14:29:08")))
                .andExpect(jsonPath('$.running', is(false)))
                .andExpect(jsonPath('$.projectName', is("My First Project")))
    }

    @Test
    @Order(2)
    void whenGetTimeEntryByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(get("/time_entry/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'TimeEntry' with attribute '3' found!")))
                .andExpect(jsonPath('$.path', is("/time_entry/3")))
    }

    @Test
    @Order(3)
    void whenGetDurationByTimeEntryId_thenReturnDuration_withStatus200() throws Exception {

        mvc.perform(get("/time_entry/1/duration")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.duration', notNullValue()))
                .andExpect(jsonPath('$.duration', isA(Integer)))
    }

    @Test
    @Order(4)
    void whenGetDurationByWrongTimeEntryId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(get("/time_entry/3/duration")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'TimeEntry' with attribute '3' found!")))
                .andExpect(jsonPath('$.path', is("/time_entry/3/duration")))
    }

    @Test
    @Order(5)
    void whenCreateTimeEntry_thenReturnTimeEntry_withStatus201() throws Exception {

        // when
        TimeEntry timeEntry = new TimeEntry()
        LocalDateTime dateTime = LocalDateTime.of(2021, 01, 01, 14, 30, 00)

        timeEntry.project = projectService.findByName("My Second Project")
        timeEntry.description = "Test"
        timeEntry.startDate = dateTime

        mvc.perform(post("/time_entry/")
                .content(timeEntry.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.description', is("Test")))
                .andExpect(jsonPath('$.running', is(true)))
                .andExpect(jsonPath('$.startDate', notNullValue()))
                .andExpect(jsonPath('$.endDate', emptyOrNullString()))
                .andExpect(jsonPath('$.projectName', is("My Second Project")))
    }

    @Test
    @Order(6)
    void whenGetAllTimeEntries_thenReturnTimeEntries_withStatus200() throws Exception {

        mvc.perform(get("/time_entry")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].startDate', is("2021-01-01T14:28:42")))
                .andExpect(jsonPath('$[0].endDate', is("2021-01-01T14:29:08")))
                .andExpect(jsonPath('$[0].running', is(false)))
                .andExpect(jsonPath('$[0].projectName', is("My First Project")))
                .andExpect(jsonPath('$[1].id', is(2)))
                .andExpect(jsonPath('$[1].startDate', is("2021-01-01T14:29:08")))
                .andExpect(jsonPath('$[1].endDate', notNullValue()))
                .andExpect(jsonPath('$[1].running', is(false)))
                .andExpect(jsonPath('$[1].projectName', is("My First Project")))
                .andExpect(jsonPath('$[2].id', is(3)))
                .andExpect(jsonPath('$[2].startDate', notNullValue()))
                .andExpect(jsonPath('$[2].endDate', emptyOrNullString()))
                .andExpect(jsonPath('$[2].running', is(true)))
                .andExpect(jsonPath('$[2].projectName', is("My Second Project")))
    }

    @Test
    @Order(7)
    void whenStopCurrentTimeEntry_thenReturnStoppedTimeEntry_withStatus200() throws Exception {

        mvc.perform(put("/time_entry/stop")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.description', is("Test")))
                .andExpect(jsonPath('$.running', is(false)))
                .andExpect(jsonPath('$.startDate', notNullValue()))
                .andExpect(jsonPath('$.endDate', notNullValue()))
                .andExpect(jsonPath('$.projectName', is("My Second Project")))
    }

    @Test
    @Order(8)
    void whenStopAlreadyStoppedTimeEntry_thenReturnException_withStatus404() throws Exception {

        mvc.perform(put("/time_entry/stop")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No running 'TimeEntry' found!")))
                .andExpect(jsonPath('$.path', is("/time_entry/stop")))
    }

    @Test
    @Order(9)
    void whenRestartLastTimeEntry_thenReturnRestartedTimeEntry_withStatus200() throws Exception {

        mvc.perform(put("/time_entry/restart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.description', is("Test")))
                .andExpect(jsonPath('$.running', is(true)))
                .andExpect(jsonPath('$.startDate', notNullValue()))
                .andExpect(jsonPath('$.endDate', emptyOrNullString()))
                .andExpect(jsonPath('$.projectName', is("My Second Project")))
    }

    @Test
    @Order(10)
    void whenRestartAlreadyRunningTimeEntry_thenReturnException_withStatus409() throws Exception {

        mvc.perform(put("/time_entry/restart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(409)))
                .andExpect(jsonPath('$.message', is("A 'TimeEntry' is already running!")))
                .andExpect(jsonPath('$.path', is("/time_entry/restart")))
    }

    @Test
    @Order(11)
    void whenUpdateTimeEntry_thenReturnUpdatedTimeEntry_withStatus200() throws Exception {

        mvc.perform(put("/time_entry/3")
                .content('{"description": "Test with update", "projectName": "My First Project"}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.description', is("Test with update")))
                .andExpect(jsonPath('$.running', is(true)))
                .andExpect(jsonPath('$.startDate', notNullValue()))
                .andExpect(jsonPath('$.endDate', emptyOrNullString()))
                .andExpect(jsonPath('$.projectName', is("My First Project")))
    }

    @Test
    @Order(12)
    void whenDeleteTimeEntryById_thenReturnDeletedTimeEntry_withStatus200() throws Exception {

        mvc.perform(delete("/time_entry/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.startDate', is("2021-01-01T14:28:42")))
                .andExpect(jsonPath('$.running', is(false)))
                .andExpect(jsonPath('$.endDate', notNullValue()))
                .andExpect(jsonPath('$.projectName', is("My First Project")))
    }

    @Test
    @Order(12)
    void whenDeleteRunningTimeEntryById_thenReturnException_withStatus409() throws Exception {

        mvc.perform(delete("/time_entry/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(409)))
                .andExpect(jsonPath('$.message', is("The 'TimeEntry' is still running!")))
                .andExpect(jsonPath('$.path', is("/time_entry/3")))
    }

    @Test
    @Order(13)
    void whenForceDeleteTimeEntryById_thenReturnDeletedTimeEntry_withStatus200() throws Exception {

        mvc.perform(delete("/time_entry/3/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.description', is("Test with update")))
                .andExpect(jsonPath('$.running', is(true)))
                .andExpect(jsonPath('$.startDate', notNullValue()))
                .andExpect(jsonPath('$.endDate', emptyOrNullString()))
                .andExpect(jsonPath('$.projectName', is("My First Project")))
    }

    @Test
    @Order(14)
    void whenForceDeleteTimeEntryByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(delete("/time_entry/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'TimeEntry' with attribute '3' found!")))
                .andExpect(jsonPath('$.path', is("/time_entry/3")))
    }
}
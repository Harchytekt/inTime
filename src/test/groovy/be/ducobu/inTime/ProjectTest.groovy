package be.ducobu.inTime

import be.ducobu.inTime.model.Project
import be.ducobu.inTime.service.ClientService
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectTest extends GroovyTestCase {

    @Autowired
    private MockMvc mvc

    @Autowired
    private ClientService clientService

    @Test
    @Order(1)
    void whenGetProjectById_thenReturnProject_withStatus200() throws Exception {

        mvc.perform(get("/project/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath('$.id', is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.name', is("My First Project")))
                .andExpect(jsonPath('$.billable', is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.clientName', is("My First Client")))
    }

    @Test
    @Order(2)
    void whenGetProjectByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(get("/project/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath('$.status', is(404)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.message', is("No 'Project' with attribute '3' found!")))
                .andExpect(MockMvcResultMatchers.jsonPath('$.path', is("/project/3")))
    }

    @Test
    @Order(3)
    void whenCreateProject_thenReturnProject_withStatus201() throws Exception {

        // when
        Project project = new Project()

        project.client = clientService.findByName("My Second Client")
        project.name = "My third Project"

        mvc.perform(post("/project/")
                .content(project.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath('$.id', is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.name', is("My third Project")))
                .andExpect(jsonPath('$.billable', is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.clientName', is("My Second Client")))
    }

    @Test
    @Order(4)
    void whenGetAllProjects_thenReturnProjects_withStatus200() throws Exception {

        mvc.perform(get("/project")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[0].id', is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[0].name', is("My First Project")))
                .andExpect(jsonPath('$[0].billable', is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[0].clientName', is("My First Client")))
                .andExpect(MockMvcResultMatchers.jsonPath('$[1].id', is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[1].name', is("My Second Project")))
                .andExpect(jsonPath('$[1].billable', is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[1].clientName', is("My Second Client")))
                .andExpect(MockMvcResultMatchers.jsonPath('$[2].id', is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[2].name', is("My third Project")))
                .andExpect(jsonPath('$[2].billable', is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[2].clientName', is("My Second Client")))
    }

    @Test
    @Order(5)
    void whenGetAllTimeEntriesByProjectId_thenReturnProjects_withStatus200() throws Exception {

        mvc.perform(get("/project/1/timeentries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(2)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].startDate', is("2021-01-01T14:28:42")))
                .andExpect(jsonPath('$[0].endDate', is("2021-01-01T14:29:08")))
                .andExpect(jsonPath('$[0].running', is(false)))
                .andExpect(jsonPath('$[0].projectName', is("My First Project")))
                .andExpect(jsonPath('$[1].id', is(2)))
                .andExpect(jsonPath('$[1].startDate', is("2021-01-01T14:29:08")))
                .andExpect(jsonPath('$[1].endDate', emptyOrNullString()))
                .andExpect(jsonPath('$[1].running', is(true)))
                .andExpect(jsonPath('$[1].projectName', is("My First Project")))
    }

    @Test
    @Order(6)
    void whenUpdateProject_thenReturnUpdatedProject_withStatus200() throws Exception {

        mvc.perform(put("/project/3")
                .content('{"name": "My Third Project", "billable": true}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath('$.id', is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.name', is("My Third Project")))
                .andExpect(jsonPath('$.billable', is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath('$.clientName', is("My Second Client")))
    }
}
package be.ducobu.inTime

import be.ducobu.inTime.model.Client
import be.ducobu.inTime.service.WorkspaceService
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

import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientTest {

    @Autowired
    private MockMvc mvc

    @Autowired
    private WorkspaceService workspaceService

    @Test
    @Order(1)
    void whenGetClientById_thenReturnClient_withStatus200() throws Exception {

        mvc.perform(get("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.name', is("My First Client")))
                .andExpect(jsonPath('$.workspaceName', is("My First Workspace")))
    }

    @Test
    @Order(2)
    void whenGetClientByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(get("/client/404")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'Client' with attribute '404' found!")))
                .andExpect(jsonPath('$.path', is("/client/404")))
    }

    @Test
    @Order(3)
    void whenCreateClient_thenReturnClient_withStatus201() throws Exception {

        // when
        Client client = new Client()

        client.workspace = workspaceService.findById(2)
        client.name = "My third Client"

        mvc.perform(post("/client/")
                .content(client.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.name', is("My third Client")))
                .andExpect(jsonPath('$.workspaceName', is("My Second Workspace")))
    }

    @Test
    @Order(4)
    void whenGetAllClients_thenReturnClients_withStatus200() throws Exception {

        mvc.perform(get("/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].name', is("My First Client")))
                .andExpect(jsonPath('$[0].workspaceName', is("My First Workspace")))
                .andExpect(jsonPath('$[1].id', is(2)))
                .andExpect(jsonPath('$[1].name', is("My Second Client")))
                .andExpect(jsonPath('$[1].workspaceName', is("My First Workspace")))
                .andExpect(jsonPath('$[2].id', is(3)))
                .andExpect(jsonPath('$[2].name', is("My third Client")))
                .andExpect(jsonPath('$[2].workspaceName', is("My Second Workspace")))
    }

    @Test
    @Order(5)
    void whenGetAllProjectsByClientId_thenReturnProjects_withStatus200() throws Exception {

        mvc.perform(get("/client/1/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(2)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].name', is("My First Project")))
                .andExpect(jsonPath('$[0].clientName', is("My First Client")))
                .andExpect(jsonPath('$[1].id', is(2)))
                .andExpect(jsonPath('$[1].name', is("My Second Project")))
                .andExpect(jsonPath('$[1].clientName', is("My First Client")))
    }

    @Test
    @Order(6)
    void whenUpdateClient_thenReturnUpdatedClient_withStatus200() throws Exception {

        mvc.perform(put("/client/3")
                .content('{"name": "My Third Client"}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.name', is("My Third Client")))
                .andExpect(jsonPath('$.workspaceName', is("My Second Workspace")))
    }

    @Test
    @Order(7)
    void whenUpdateClientWithEmptyBody_thenReturnException_withStatus400() throws Exception {

        mvc.perform(put("/client/3")
                .content('{}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(400)))
                .andExpect(jsonPath('$.message', is("The entity 'Client' with attribute '3' couldn't be updated! Nothing was sent in the body.")))
                .andExpect(jsonPath('$.path', is("/client/3")))
    }

    @Test
    @Order(8)
    void whenUpdateClientWithNoChange_thenReturnException_withStatus400() throws Exception {

        mvc.perform(put("/client/3")
                .content('{"name": "My Third Client"}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(400)))
                .andExpect(jsonPath('$.message', is("The entity 'Client' with attribute '3' couldn't be updated! Please check the changes you've made.")))
                .andExpect(jsonPath('$.path', is("/client/3")))
    }

    @Test
    @Order(9)
    void whenDeleteClientById_thenReturnDeletedClient_withStatus200() throws Exception {

        mvc.perform(delete("/client/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.name', is("My Third Client")))
                .andExpect(jsonPath('$.workspaceName', is("My Second Workspace")))
    }

    @Test
    @Order(10)
    void whenDeleteClientWithChildrenById_thenReturnException_withStatus409() throws Exception {

        mvc.perform(delete("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(409)))
                .andExpect(jsonPath('$.message', is("One or more 'Project' still linked to this entity.")))
                .andExpect(jsonPath('$.path', is("/client/1")))
    }

    @Test
    @Order(11)
    void whenForceDeleteClientWithChildrenById_thenReturnDeletedClient_withStatus200() throws Exception {

        mvc.perform(delete("/client/1/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.name', is("My First Client")))
                .andExpect(jsonPath('$.workspaceName', is("My First Workspace")))
    }

    @Test
    @Order(12)
    void whenForceDeleteClientByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(delete("/client/1/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'Client' with attribute '1' found!")))
                .andExpect(jsonPath('$.path', is("/client/1/force")))
    }
}
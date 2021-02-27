package be.ducobu.inTime

import be.ducobu.inTime.model.Workspace
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

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorkspaceTest extends GroovyTestCase {

    @Autowired
    private MockMvc mvc

    @Test
    @Order(1)
    void whenGetWorkspaceById_thenReturnWorkspace_withStatus200() throws Exception {

        mvc.perform(get("/workspace/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.name', is("My First Workspace")))
    }

    @Test
    @Order(2)
    void whenGetWorkspaceByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(get("/workspace/404")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'Workspace' with attribute '404' found!")))
                .andExpect(jsonPath('$.path', is("/workspace/404")))
    }

    @Test
    @Order(3)
    void whenCreateWorkspace_thenReturnWorkspace_withStatus201() throws Exception {

        // when
        Workspace workspace = new Workspace()

        workspace.name = "My third Workspace"

        mvc.perform(post("/workspace/")
                .content(workspace.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.name', is("My third Workspace")))
    }

    @Test
    @Order(4)
    void whenGetAllWorkspaces_thenReturnWorkspaces_withStatus200() throws Exception {

        mvc.perform(get("/workspace")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].name', is("My First Workspace")))
                .andExpect(jsonPath('$[1].id', is(2)))
                .andExpect(jsonPath('$[1].name', is("My Second Workspace")))
                .andExpect(jsonPath('$[2].id', is(3)))
                .andExpect(jsonPath('$[2].name', is("My third Workspace")))
    }

    @Test
    @Order(5)
    void whenGetAllClientsByWorkspaceId_thenReturnClients_withStatus200() throws Exception {

        mvc.perform(get("/workspace/1/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(2)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].name', is("My First Client")))
                .andExpect(jsonPath('$[0].workspaceName', is("My First Workspace")))
                .andExpect(jsonPath('$[1].id', is(2)))
                .andExpect(jsonPath('$[1].name', is("My Second Client")))
                .andExpect(jsonPath('$[1].workspaceName', is("My First Workspace")))
    }

    @Test
    @Order(6)
    void whenUpdateWorkspace_thenReturnUpdatedWorkspace_withStatus200() throws Exception {

        mvc.perform(put("/workspace/3")
                .content('{"name": "My Third Workspace", "togglId": 3}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.togglId', is(3)))
                .andExpect(jsonPath('$.name', is("My Third Workspace")))
    }

    @Test
    @Order(9)
    void whenDeleteTogglIdWorkspace_thenReturnUpdatedWorkspace_withStatus200() throws Exception {

        mvc.perform(put("/workspace/3/togglid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.togglId', emptyOrNullString()))
                .andExpect(jsonPath('$.name', is("My Third Workspace")))
    }

    @Test
    @Order(10)
    void whenDeleteWorkspaceById_thenReturnDeletedWorkspace_withStatus200() throws Exception {

        mvc.perform(delete("/workspace/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.name', is("My Third Workspace")))
    }

    @Test
    @Order(11)
    void whenDeleteWorkspaceWithChildrenById_thenReturnException_withStatus409() throws Exception {

        mvc.perform(delete("/workspace/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(409)))
                .andExpect(jsonPath('$.message', is("One or more 'Client' still linked to this entity.")))
                .andExpect(jsonPath('$.path', is("/workspace/1")))
    }

    @Test
    @Order(12)
    void whenForceDeleteWorkspaceWithChildrenById_thenReturnDeletedWorkspace_withStatus200() throws Exception {

        mvc.perform(delete("/workspace/1/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.name', is("My First Workspace")))
    }

    @Test
    @Order(13)
    void whenForceDeleteWorkspaceByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(delete("/workspace/1/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'Workspace' with attribute '1' found!")))
                .andExpect(jsonPath('$.path', is("/workspace/1/force")))
    }
}
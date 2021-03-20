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
    @Order(7)
    void whenUpdateWorkspaceWithEmptyBody_thenReturnException_withStatus400() throws Exception {

        mvc.perform(put("/workspace/3")
                .content('{}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(400)))
                .andExpect(jsonPath('$.message', is("The entity 'Workspace' with attribute '3' couldn't be updated! Nothing was sent in the body.")))
                .andExpect(jsonPath('$.path', is("/workspace/3")))
    }

    @Test
    @Order(8)
    void whenUpdateWorkspaceWithNoChange_thenReturnException_withStatus400() throws Exception {

        mvc.perform(put("/workspace/3")
                .content('{"name": "My Third Workspace"}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(400)))
                .andExpect(jsonPath('$.message', is("The entity 'Workspace' with attribute '3' couldn't be updated! Please check the changes you've made.")))
                .andExpect(jsonPath('$.path', is("/workspace/3")))
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
    void whenDeleteAlreadyNullTogglIdWorkspace_thenReturnException_withStatus409() throws Exception {

        mvc.perform(put("/workspace/1/togglid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(409)))
                .andExpect(jsonPath('$.message', is("There is no Toggl ID linked to the entity 'Workspace' with id '1'!")))
                .andExpect(jsonPath('$.path', is("/workspace/1/togglid")))
    }

    @Test
    @Order(11)
    void whenDeleteWorkspaceById_thenReturnDeletedWorkspace_withStatus200() throws Exception {

        mvc.perform(delete("/workspace/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(3)))
                .andExpect(jsonPath('$.name', is("My Third Workspace")))
    }

    @Test
    @Order(12)
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
    @Order(13)
    void whenForceDeleteWorkspaceWithChildrenById_thenReturnDeletedWorkspace_withStatus200() throws Exception {

        mvc.perform(delete("/workspace/1/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(1)))
                .andExpect(jsonPath('$.name', is("My First Workspace")))
    }

    @Test
    @Order(14)
    void whenForceDeleteWorkspaceByWrongId_thenReturnException_withStatus404() throws Exception {

        mvc.perform(delete("/workspace/1/force")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(404)))
                .andExpect(jsonPath('$.message', is("No 'Workspace' with attribute '1' found!")))
                .andExpect(jsonPath('$.path', is("/workspace/1/force")))
    }

    @Test
    @Order(15)
    void whenCreateWorkspaceWithTogglId_thenReturnWorkspace_withStatus201() throws Exception {

        // when
        Workspace workspace = new Workspace()

        workspace.name = "My Fourth Workspace"
        workspace.togglId = 42

        mvc.perform(post("/workspace/")
                .content(workspace.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(4)))
                .andExpect(jsonPath('$.name', is("My Fourth Workspace")))
                .andExpect(jsonPath('$.togglId', is(42)))
    }

    @Test
    @Order(16)
    void whenCreateWorkspaceWithNullTogglId_thenReturnWorkspace_withStatus201() throws Exception {

        // when
        Workspace workspace = new Workspace()

        workspace.name = "My Fifth Workspace"
        workspace.togglId = null // or 0 (it's the same)

        mvc.perform(post("/workspace/")
                .content(workspace.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id', is(5)))
                .andExpect(jsonPath('$.name', is("My Fifth Workspace")))
                .andExpect(jsonPath('$.togglId', emptyOrNullString()))
    }

    @Test
    @Order(17)
    void whenCreateWorkspaceWithExistingTogglId_thenReturnException_withStatus409() throws Exception {

        // when
        Workspace workspace = new Workspace()

        workspace.name = "My Sixth Workspace"
        workspace.togglId = 42

        mvc.perform(post("/workspace/")
                .content(workspace.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.status', is(409)))
                .andExpect(jsonPath('$.message', is("An entity 'Workspace' with 'togglId' '42' already exist!")))
                .andExpect(jsonPath('$.path', is("/workspace/")))
    }
}
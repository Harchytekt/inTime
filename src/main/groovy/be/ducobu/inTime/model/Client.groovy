package be.ducobu.inTime.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(
        name = "clients",
        uniqueConstraints = @UniqueConstraint(columnNames = ["name", "toggl_id"])
)
class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id

    @Column(name = "toggl_id")
    private Long togglId
    @Column
    @NotNull
    private String name

    @ManyToOne
    @JoinColumn(name = "fk_workspace", nullable = false)
    private Workspace workspace

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Project> projects

    Client() {}

    Client(Long id, String name) {
        this.id = id
        this.name = name
    }

    Client(Client clientToCopy) {
        this.id = clientToCopy.id
        this.name = clientToCopy.name
        this.togglId = clientToCopy.togglId
        this.workspace = clientToCopy.workspace
        this.projects = clientToCopy.projects
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Long getTogglId() {
        return togglId
    }

    void setTogglId(Long togglId) {
        this.togglId = togglId
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Workspace getWorkspace() {
        return workspace
    }

    void setWorkspace(Workspace workspace) {
        this.workspace = workspace
    }

    Set<Project> getProjects() {
        return projects
    }

    void setProjects(Set<Project> projects) {
        this.projects = projects
    }

    String toJson() {
        return "{\"name\": \"$name\", \"workspaceName\": \"${workspace.name}\"}"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Client client = (Client) o

        if (id != client.id) return false
        if (name != client.name) return false
        if (projects != client.projects) return false
        if (togglId != client.togglId) return false
        if (workspace != client.workspace) return false

        return true
    }
}

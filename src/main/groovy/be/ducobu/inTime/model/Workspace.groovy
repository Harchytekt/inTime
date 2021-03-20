package be.ducobu.inTime.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(
        name = "workspaces",
        uniqueConstraints = @UniqueConstraint(columnNames = ["name", "toggl_id"])
)
class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id

    @Column(name = "toggl_id")
    private Long togglId
    @Column
    @NotNull
    private String name

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workspace", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Client> clients

    Workspace() {}

    Workspace(Long id, String name) {
        this.id = id
        this.name = name
    }

    Workspace(Workspace workspaceToCopy) {
        this.id = workspaceToCopy.id
        this.name = workspaceToCopy.name
        this.togglId = workspaceToCopy.togglId
        this.clients = workspaceToCopy.clients
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

    Set<Client> getClients() {
        return clients
    }

    void setClients(Set<Client> clients) {
        this.clients = clients
    }

    String toJson() {
        return "{\"name\": \"$name\", \"togglId\": \"$togglId\"}"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Workspace workspace = (Workspace) o

        if (clients != workspace.clients) return false
        if (id != workspace.id) return false
        if (name != workspace.name) return false
        if (togglId != workspace.togglId) return false

        return true
    }
}

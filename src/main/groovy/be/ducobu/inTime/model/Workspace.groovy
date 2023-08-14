package be.ducobu.inTime.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(
        name = "workspaces",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id

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
        this.clients = workspaceToCopy.clients
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
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

    boolean hasClients() {
        return !this.clients.isEmpty()
    }

    String toJson() {
        return "{\"name\": \"$name\"}"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Workspace workspace = (Workspace) o

        if (clients != workspace.clients) return false
        if (id != workspace.id) return false
        if (name != workspace.name) return false

        return true
    }
}

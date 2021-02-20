package be.ducobu.inTime.model

import javax.persistence.*

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

    @Column(name = "toggl_id")
    private Long togglId
    @Column
    private String name

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workspace", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Client> clients

    Workspace() {}

    Workspace(Long id, String name) {
        this.id = id
        this.name = name
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
        return "{\"name\": \"$name\"}"
    }
}

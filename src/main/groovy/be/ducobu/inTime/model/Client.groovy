package be.ducobu.inTime.model

import javax.persistence.*

@Entity
@Table(
        name = "clients",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id

    @Column(name = "toggl_id")
    private Long togglId
    @Column
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
}

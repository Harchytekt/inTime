package be.ducobu.inTime.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(
        name = "projects",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id

    @Column
    @NotNull
    private String name

    @ManyToOne
    @JoinColumn(name = "fk_client", nullable = false)
    private Client client

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<TimeEntry> timeEntries

    Project() {}

    Project(Long id, String name) {
        this.id = id
        this.name = name
    }

    Project(Project projectToCopy) {
        this.id = projectToCopy.id
        this.name = projectToCopy.name
        this.client = projectToCopy.client
        this.timeEntries = projectToCopy.timeEntries
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

    Client getClient() {
        return client
    }

    void setClient(Client client) {
        this.client = client
    }

    List<TimeEntry> getTimeEntries() {
        return timeEntries
    }

    boolean hasTimeEntries() {
        return !this.timeEntries.isEmpty()
    }

    String toJson() {
        return "{\"name\": \"$name\", \"clientName\": \"${client.name}\"}"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Project project = (Project) o

        if (client != project.client) return false
        if (id != project.id) return false
        if (name != project.name) return false
        if (timeEntries != project.timeEntries) return false

        return true
    }
}

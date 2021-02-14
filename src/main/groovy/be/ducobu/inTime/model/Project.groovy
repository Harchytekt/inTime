package be.ducobu.inTime.model

import javax.persistence.*

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

    @Column(name = "toggl_id")
    private Long togglId
    @Column
    private String name
    @Column
    private boolean billable

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

    boolean getBillable() {
        return billable
    }

    void setBillable(boolean billable) {
        this.billable = billable
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

    void setTimeEntries(List<TimeEntry> timeEntries) {
        this.timeEntries = timeEntries
    }
}

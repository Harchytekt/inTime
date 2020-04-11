package be.ducobu.inTime.model

import javax.persistence.*

@Entity
@Table(name = "time_entries")
class TimeEntry {

    @Id
    @Column
    private Long id

    @Column(name = "toggl_id")
    private Long togglId
    @Column(name = "start_date")
    private Date startDate
    @Column(name = "end_date")
    private Date endDate
    @Column
    private Integer duration = 0
    @Column
    private String description
    @Column
    private boolean running

    @ManyToOne
    @JoinColumn(name = "fk_project", nullable = false)
    private Project project

    TimeEntry() {

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

    Date getStartDate() {
        return startDate
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate
    }

    Date getEndDate() {
        return endDate
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate
    }

    Integer getDuration() {
        return duration
    }

    void setDuration(Integer duration) {
        this.duration = duration
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    boolean getRunning() {
        return running
    }

    void setRunning(boolean running) {
        this.running = running
    }

    Project getProject() {
        return project
    }

    void setProject(Project project) {
        this.project = project
    }

    @Override
    String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", description='" + description + '\'' +
                '}';
    }
}

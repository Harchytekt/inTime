package be.ducobu.inTime.model

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "time_entries")
class TimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id

    @Column(name = "toggl_id")
    private Long togglId
    @Column(name = "start_date")
    private LocalDateTime startDate = LocalDateTime.now()
    @Column(name = "end_date")
    private LocalDateTime endDate
    @Column
    private Long duration
    @Column
    private String description
    @Column
    private boolean running = true

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

    LocalDateTime getStartDate() {
        return startDate
    }

    void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate
    }

    LocalDateTime getEndDate() {
        return endDate
    }

    void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate
    }

    Long getDuration() {
        return duration
    }

    void setDuration(Long duration) {
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
                '}'
    }
}

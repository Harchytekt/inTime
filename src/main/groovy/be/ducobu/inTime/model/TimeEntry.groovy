package be.ducobu.inTime.model

import javax.persistence.*
import java.time.Duration
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

    TimeEntry() {}

    TimeEntry(TimeEntry timeEntryToCopy) {
        this.id = timeEntryToCopy.id
        this.togglId = timeEntryToCopy.togglId
        this.startDate = timeEntryToCopy.startDate
        this.endDate = timeEntryToCopy.endDate
        this.duration = timeEntryToCopy.duration
        this.description = timeEntryToCopy.description
        this.running = timeEntryToCopy.running
        this.project = timeEntryToCopy.project
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
        if (duration == null)
            return calculateDuration(false)
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
                ", description='" + description + "'" +
                "}"
    }

    String toJson() {
        return "{\"projectName\": \"${project.name}\", \"description\": \"$description\"}"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        TimeEntry timeEntry = (TimeEntry) o

        if (running != timeEntry.running) return false
        if (description != timeEntry.description) return false
        if (!running && duration != timeEntry.duration) return false
        if (endDate != timeEntry.endDate) return false
        if (id != timeEntry.id) return false
        if (project != timeEntry.project) return false
        if (startDate != timeEntry.startDate) return false
        if (togglId != timeEntry.togglId) return false

        return true
    }

    Long calculateDuration(boolean isStopped = true) {
        return Duration.between(
                startDate,
                isStopped ? endDate : LocalDateTime.now()
        ).getSeconds()
    }

    void stop() {
        this.endDate = LocalDateTime.now()
        this.duration = calculateDuration()
        this.running = false
    }

    void updateEndDate(LocalDateTime endDate) {
        this.endDate = endDate
        this.duration = calculateDuration()
    }

    void restart() {
        this.endDate = null
        this.duration = null
        this.running = true
    }
}

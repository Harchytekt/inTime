package be.ducobu.inTime.dto.timeEntry

import com.fasterxml.jackson.annotation.JsonPropertyOrder

import java.time.LocalDateTime

@JsonPropertyOrder(["id", "startDate", "endDate", "duration", "description", "running", "projectId"])
class TimeEntryDto {

    private Long id
    private LocalDateTime startDate
    private LocalDateTime endDate
    private Integer duration
    private String description
    private boolean running
    private Long projectId

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getStartDate() {
        return startDate
    }

    void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate
    }

    String getEndDate() {
        return endDate
    }

    void setEndDate(LocalDateTime endDate) {
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

    Long getProjectId() {
        return projectId
    }

    void setProjectId(Long projectId) {
        this.projectId = projectId
    }
}

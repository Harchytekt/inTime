package be.ducobu.inTime.dto

import java.time.LocalDateTime

class TimeEntryDto {

    private Long id
    private Long toggleId
    private LocalDateTime startDate
    private LocalDateTime endDate
    private Integer duration
    private String description
    private boolean running
    private String projectName

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Long getToggleId() {
        return toggleId
    }

    void setToggleId(Long toggleId) {
        this.toggleId = toggleId
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

    String getProjectName() {
        return projectName
    }

    void setProjectName(String projectName) {
        this.projectName = projectName
    }
}

package be.ducobu.inTime.dto

import java.time.LocalDateTime

class TimeEntrySaveDto {

    private Long id
    private Long projectId
    private LocalDateTime startDate
    private String description

    TimeEntrySaveDto(Long projectId, LocalDateTime startDate, String description) {
        this.projectId = projectId
        this.startDate = startDate
        this.description = description
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Long getProjectId() {
        return projectId
    }

    void setProjectId(Long projectId) {
        this.projectId = projectId
    }

    LocalDateTime getStartDate() {
        return startDate
    }

    void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}

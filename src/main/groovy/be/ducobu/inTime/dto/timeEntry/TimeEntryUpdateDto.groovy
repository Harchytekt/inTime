package be.ducobu.inTime.dto.timeEntry

import java.time.LocalDateTime

class TimeEntryUpdateDto {

    private Long togglId
    private LocalDateTime startDate
    private LocalDateTime endDate
    private String description
    private String projectName

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

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    String getProjectName() {
        return projectName
    }

    void setProjectName(String projectName) {
        this.projectName = projectName
    }
}

package be.ducobu.inTime.dto.timeEntry

import be.ducobu.inTime.model.Project

import java.time.LocalDateTime

class TimeEntrySaveDto {

    private Long id
    private Project project
    private LocalDateTime startDate
    private Long togglId
    private String description

    TimeEntrySaveDto(Project project, LocalDateTime startDate, Long togglId, String description) {
        this.project = project
        this.startDate = startDate
        this.togglId = togglId
        this.description = description
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Project getProject() {
        return project
    }

    void setProject(Project project) {
        this.project = project
    }

    LocalDateTime getStartDate() {
        return startDate
    }

    void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate
    }

    Long getTogglId() {
        return togglId
    }

    void setTogglId(Long togglId) {
        this.togglId = togglId
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}

package be.ducobu.inTime.dto.timeEntry

class TimeEntryCreateDto {

    private String description
    private Long projectId
    private String projectName

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    Long getProjectId() {
        return projectId
    }

    void setProjectId(Long projectId) {
        this.projectId = projectId
    }

    String getProjectName() {
        return projectName
    }

    void setProjectName(String projectName) {
        this.projectName = projectName
    }
}

package be.ducobu.inTime.dto.timeEntry

class TimeEntryCreateDto {

    private String description
    private String projectName
    private Long togglId

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

    Long getTogglId() {
        return togglId
    }

    void setTogglId(Long togglId) {
        this.togglId = togglId
    }
}

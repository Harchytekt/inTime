package be.ducobu.inTime.dto

class TimeEntryCreateDto {

    private String projectName
    private String description

    String getProjectName() {
        return projectName
    }

    void setProjectName(String projectName) {
        this.projectName = projectName
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}

package be.ducobu.inTime.dto

class TimeEntryDto {

    private Long id
    private Long toggleId
    private Date startDate
    private Date endDate
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

    Long getToggleId() {
        return toggleId
    }

    void setToggleId(Long toggleId) {
        this.toggleId = toggleId
    }

    Date getStartDate() {
        return startDate
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate
    }

    Date getEndDate() {
        return endDate
    }

    void setEndDate(Date endDate) {
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

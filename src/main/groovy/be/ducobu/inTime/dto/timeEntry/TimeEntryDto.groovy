package be.ducobu.inTime.dto.timeEntry

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder(["id", "togglId", "startDate", "endDate", "duration", "description", "running", "projectName"])
class TimeEntryDto extends TimeEntryUpdateDto {

    private Long id
    private Integer duration
    private boolean running

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Integer getDuration() {
        return duration
    }

    void setDuration(Integer duration) {
        this.duration = duration
    }

    boolean getRunning() {
        return running
    }

    void setRunning(boolean running) {
        this.running = running
    }
}

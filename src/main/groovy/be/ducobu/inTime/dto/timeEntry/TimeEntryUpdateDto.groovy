package be.ducobu.inTime.dto.timeEntry

import java.time.LocalDateTime

class TimeEntryUpdateDto extends TimeEntryCreateDto {

    private Long togglId
    private LocalDateTime startDate
    private LocalDateTime endDate

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
}
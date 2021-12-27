package be.ducobu.inTime.dto.timeEntry

import java.time.LocalDateTime

class TimeEntryUpdateDto extends TimeEntryCreateDto {

    private LocalDateTime startDate
    private LocalDateTime endDate

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

    boolean isEmpty() {
        return description == null && projectName == null && startDate == null && endDate == null
    }
}
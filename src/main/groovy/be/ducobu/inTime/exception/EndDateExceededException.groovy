package be.ducobu.inTime.exception

import java.time.LocalDateTime

class EndDateExceededException extends RuntimeException {
    EndDateExceededException(LocalDateTime startDate, LocalDateTime endDate) {
        super("The end date should be after the start date: '$startDate' > '$endDate'!")
    }
}

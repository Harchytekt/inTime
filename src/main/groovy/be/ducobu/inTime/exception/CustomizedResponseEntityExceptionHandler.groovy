package be.ducobu.inTime.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String SOURCE_CANNOT_BE_NULL = "source cannot be null"
    private static HttpStatus status
    private static String message

    @ExceptionHandler(Exception.class)
    final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        StringWriter sw = new StringWriter()
        ex.printStackTrace(new PrintWriter(sw))

        status = HttpStatus.INTERNAL_SERVER_ERROR
        logger.error(status)

        message = "A technical problem has occurred!"

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                status.value(),
                message,
                request.getDescription(false)
        )
        return new ResponseEntity<>(exceptionResponse, status)
    }

    @ExceptionHandler(WrongRestCallException.class)
    final ResponseEntity<Object> handleWrongRestCallException(WrongRestCallException ex, WebRequest request) {
        logger.error(ex.getMessage())
        status = HttpStatus.BAD_REQUEST

        if (SOURCE_CANNOT_BE_NULL == ex.getMessage()) {
            message = "The chosen item doesn't exist!"
            logger.error(message)
        } else {
            message = ex.getMessage()
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                status.value(),
                message,
                request.getDescription(false)
        )
        return new ResponseEntity<>(exceptionResponse, status)
    }

    @ExceptionHandler(CustomEntityNotFoundException.class)
    final ResponseEntity<Object> handleCustomEntityNotFoundException(CustomEntityNotFoundException ex, WebRequest request) {
        logger.error(ex.getMessage())
        status = HttpStatus.NOT_FOUND

        message = ex.getMessage()

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                status.value(),
                message,
                request.getDescription(false)
        )
        return new ResponseEntity<>(exceptionResponse, status)
    }

    @ExceptionHandler(RunningTimeEntryNotFoundException.class)
    final ResponseEntity<Object> handleRunningTimeEntryNotFoundException(RunningTimeEntryNotFoundException ex, WebRequest request) {
        logger.error(ex.getMessage())
        status = HttpStatus.NOT_FOUND

        message = ex.getMessage()

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                status.value(),
                message,
                request.getDescription(false)
        )
        return new ResponseEntity<>(exceptionResponse, status)
    }

    @ExceptionHandler(DuplicateEntryException.class)
    final ResponseEntity<Object> handleDuplicateEntryException(DuplicateEntryException ex, WebRequest request) {
        logger.error(ex.getMessage())
        status = HttpStatus.CONFLICT

        message = ex.getMessage()

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                status.value(),
                message,
                request.getDescription(false)
        )
        return new ResponseEntity<>(exceptionResponse, status)
    }

    @ExceptionHandler(RunningTimeEntryException.class)
    final ResponseEntity<Object> handleRunningTimeEntryException(RunningTimeEntryException ex, WebRequest request) {
        logger.error(ex.getMessage())
        status = HttpStatus.CONFLICT

        message = ex.getMessage()

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                status.value(),
                message,
                request.getDescription(false)
        )
        return new ResponseEntity<>(exceptionResponse, status)
    }


}

package be.ducobu.inTime.exception

import java.time.LocalDateTime

class ExceptionResponse {
    private String timestamp
    private Integer status
    private String message
    private String path

    ExceptionResponse(LocalDateTime timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp
        this.status = status
        this.message = message
        this.path = path.startsWith("uri=") ? path.split("=")[1] : path
    }

    String getTimestamp() {
        return timestamp
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp.toString()
    }

    Integer getStatus() {
        return status
    }

    void setStatus(Integer status) {
        this.status = status
    }

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    String getPath() {
        return path
    }

    void setPath(String path) {
        this.path = path
    }
}


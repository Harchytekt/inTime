package be.ducobu.inTime.exception

class ExceptionResponse {
    private Date timestamp
    private Integer status
    private String message
    private String path

    ExceptionResponse(Date timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp
        this.status = status
        this.message = message
        this.path = path.startsWith("uri=") ? path.split("=")[1] : path
    }

    Date getTimestamp() {
        return timestamp
    }

    void setTimestamp(Date timestamp) {
        this.timestamp = timestamp
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


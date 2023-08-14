package be.ducobu.inTime.dto.project

class ProjectCreateDto {

    private String name
    private Long clientId
    private String clientName

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getClientName() {
        return clientName
    }

    Long getClientId() {
        return clientId
    }

    void setClientId(Long clientId) {
        this.clientId = clientId
    }

    void setClientName(String clientName) {
        this.clientName = clientName
    }

    boolean isEmpty() {
        return name == null && clientName == null
    }
}

package be.ducobu.inTime.dto.project

class ProjectDto {

    private Long id
    private String name
    private Long clientId

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Long getClientId() {
        return clientId
    }

    void setClientId(Long clientId) {
        this.clientId = clientId
    }
}

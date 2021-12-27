package be.ducobu.inTime.dto.project

class ProjectDto {

    private Long id
    private String name
    private String clientName

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

    String getClientName() {
        return clientName
    }

    void setClientName(String clientName) {
        this.clientName = clientName
    }
}

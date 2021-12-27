package be.ducobu.inTime.dto.client

class ClientDto {

    private Long id
    private String name
    private String workspaceName

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

    String getWorkspaceName() {
        return workspaceName
    }

    void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName
    }
}

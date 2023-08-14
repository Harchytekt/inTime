package be.ducobu.inTime.dto.client

class ClientDto {

    private Long id
    private String name
    private Long workspaceId

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

    Long getWorkspaceId() {
        return workspaceId
    }

    void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId
    }
}

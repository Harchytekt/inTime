package be.ducobu.inTime.dto.client

class ClientCreateDto {

    private String name
    private Long workspaceId
    private String workspaceName

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

    String getWorkspaceName() {
        return workspaceName
    }

    void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName
    }

    boolean isEmpty() {
        return name == null && workspaceName == null
    }
}

package be.ducobu.inTime.dto.client

class ClientCreateDto {

    private String name
    private String workspaceName

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

    boolean isEmpty() {
        return name == null && workspaceName == null
    }
}

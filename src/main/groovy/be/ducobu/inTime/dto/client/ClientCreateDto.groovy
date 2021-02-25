package be.ducobu.inTime.dto.client

class ClientCreateDto {

    private Long togglId
    private String name
    private String workspaceName

    Long getTogglId() {
        return togglId
    }

    void setTogglId(Long togglId) {
        this.togglId = togglId
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

    boolean isEmpty() {
        return name == null && workspaceName == null && togglId == null
    }
}

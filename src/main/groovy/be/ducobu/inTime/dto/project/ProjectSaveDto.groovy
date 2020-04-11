package be.ducobu.inTime.dto.project

class ProjectSaveDto {

    private Long id
    private String name
    private boolean billable
    private Long workspaceId
    private Long clientId

    ProjectSaveDto(String name, boolean billable, Long workspaceId, Long clientId) {
        this.name = name
        this.billable = billable
        this.workspaceId = workspaceId
        this.clientId = clientId
    }

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

    boolean getBillable() {
        return billable
    }

    void setBillable(boolean billable) {
        this.billable = billable
    }

    Long getWorkspaceId() {
        return workspaceId
    }

    void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId
    }

    Long getClientId() {
        return clientId
    }

    void setClientId(Long clientId) {
        this.clientId = clientId
    }
}

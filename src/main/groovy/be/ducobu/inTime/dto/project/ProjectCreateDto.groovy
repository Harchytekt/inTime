package be.ducobu.inTime.dto.project

class ProjectCreateDto {

    private String name
    private boolean billable
    private String workspaceName
    private String clientName

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

    String getWorkspaceName() {
        return workspaceName
    }

    void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName
    }

    String getClientName() {
        return clientName
    }

    void setClientName(String clientName) {
        this.clientName = clientName
    }
}

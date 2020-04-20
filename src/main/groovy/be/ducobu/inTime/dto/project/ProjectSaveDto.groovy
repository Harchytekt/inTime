package be.ducobu.inTime.dto.project

class ProjectSaveDto {

    private Long id
    private String name
    private boolean billable
    private Long clientId

    ProjectSaveDto(String name, boolean billable, Long clientId) {
        this.name = name
        this.billable = billable
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

    Long getClientId() {
        return clientId
    }

    void setClientId(Long clientId) {
        this.clientId = clientId
    }
}

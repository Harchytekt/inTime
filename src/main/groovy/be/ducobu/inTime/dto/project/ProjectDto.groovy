package be.ducobu.inTime.dto.project

class ProjectDto {

    private Long id
    private Long togglId
    private String name
    private boolean billable
    private String clientName

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

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

    boolean getBillable() {
        return billable
    }

    void setBillable(boolean billable) {
        this.billable = billable
    }

    String getClientName() {
        return clientName
    }

    void setClientName(String clientName) {
        this.clientName = clientName
    }
}

package be.ducobu.inTime.dto.project

class ProjectCreateDto {

    private Long togglId
    private String name
    private Boolean billable
    private String clientName

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

    Boolean getBillable() {
        return billable
    }

    void setBillable(Boolean billable) {
        this.billable = billable
    }

    String getClientName() {
        return clientName
    }

    void setClientName(String clientName) {
        this.clientName = clientName
    }
}

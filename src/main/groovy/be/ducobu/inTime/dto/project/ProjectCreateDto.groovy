package be.ducobu.inTime.dto.project

class ProjectCreateDto {

    private String name
    private String clientName

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getClientName() {
        return clientName
    }

    void setClientName(String clientName) {
        this.clientName = clientName
    }

    boolean isEmpty() {
        return name == null && clientName == null
    }
}

package be.ducobu.inTime.dto.project

import be.ducobu.inTime.model.Client

class ProjectSaveDto {

    private Long id
    private String name
    private Client client

    ProjectSaveDto(String name, Client client) {
        this.name = name
        this.client = client
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

    Client getClient() {
        return client
    }

    void setClient(Client client) {
        this.client = client
    }
}

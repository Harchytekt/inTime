package be.ducobu.inTime.dto.client

import be.ducobu.inTime.model.Workspace

class ClientSaveDto {

    private Long id
    private String name
    private Workspace workspace

    ClientSaveDto(String name, Workspace workspace) {
        this.name = name
        this.workspace = workspace
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

    Workspace getWorkspace() {
        return workspace
    }

    void setWorkspace(Workspace workspace) {
        this.workspace = workspace
    }
}

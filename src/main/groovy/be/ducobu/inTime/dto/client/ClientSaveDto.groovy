package be.ducobu.inTime.dto.client

import be.ducobu.inTime.model.Workspace

class ClientSaveDto {

    private Long id
    private String name
    private Long togglId
    private Workspace workspace

    ClientSaveDto(String name, Long togglId, Workspace workspace) {
        this.name = name
        this.togglId = togglId
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

    Long getTogglId() {
        return togglId
    }

    void setTogglId(Long togglId) {
        this.togglId = togglId
    }

    Workspace getWorkspace() {
        return workspace
    }

    void setWorkspace(Workspace workspace) {
        this.workspace = workspace
    }
}

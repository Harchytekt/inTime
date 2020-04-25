package be.ducobu.inTime.dto.workspace

class WorkspaceCreateDto {

    private Long togglId
    private String name

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
}

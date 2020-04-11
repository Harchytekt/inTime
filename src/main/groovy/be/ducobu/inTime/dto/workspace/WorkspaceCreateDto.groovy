package be.ducobu.inTime.dto.workspace

class WorkspaceCreateDto {

    private Long id
    private String name

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
}

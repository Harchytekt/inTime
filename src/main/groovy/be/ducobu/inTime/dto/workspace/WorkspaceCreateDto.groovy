package be.ducobu.inTime.dto.workspace

class WorkspaceCreateDto {

    private String name

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    boolean isEmpty() {
        return name == null
    }
}

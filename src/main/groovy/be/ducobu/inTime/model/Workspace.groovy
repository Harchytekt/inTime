package be.ducobu.inTime.model

import javax.persistence.*

@Entity
@Table(name = "workspaces")
class Workspace {

    @Id
    @Column
    private Long id

    @Column(name = "toggl_id")
    private Long togglId
    @Column
    private String name

    @OneToMany(mappedBy = "workspace")
    private Set<Client> clients
    @OneToMany(mappedBy = "workspace")
    private Set<Project> projects

    Workspace() {

    }

    Workspace(Long id, String name) {
        this.id = id
        this.name = name
    }

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

    Set<Client> getClients() {
        return clients
    }

    void setClients(Set<Client> clients) {
        this.clients = clients
    }

    Set<Project> getProjects() {
        return projects
    }

    void setProjects(Set<Project> projects) {
        this.projects = projects
    }
}

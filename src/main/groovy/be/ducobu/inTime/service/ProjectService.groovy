package be.ducobu.inTime.service

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.model.Project
import be.ducobu.inTime.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService {

    private final ProjectRepository projectRepository

    ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository
    }

    Project save(Project project) {
        return projectRepository.save(project)
    }

    List<Project> findAll() {
        return projectRepository.findAll()
    }

    Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow({ -> new CustomEntityNotFoundException("Project", id as String) })
    }

    Project findByName(String name) {
        return projectRepository.findByName(name)
                .orElseThrow({ -> new CustomEntityNotFoundException("Project", name) })
    }

    Project findByToggleId(Long togglId) {
        return projectRepository.findByTogglId(togglId)
                .orElseThrow({ -> new CustomEntityNotFoundException("Project", togglId as String) })
    }

    void deleteById(Long id) {
        projectRepository.deleteById(id)
    }

}

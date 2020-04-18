package be.ducobu.inTime.service

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.model.Project
import be.ducobu.inTime.model.TimeEntry
import be.ducobu.inTime.repository.ProjectRepository
import be.ducobu.inTime.repository.TimeEntryRepository
import org.springframework.stereotype.Service

@Service
class ProjectService {

    private final ProjectRepository projectRepository

    ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository
    }

    Long save(Project project) {
        return projectRepository.save(project).id
    }

    Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow({ -> new CustomEntityNotFoundException("Project", id as String) })
    }

    Project findByName(String name) {
        return projectRepository.findByName(name)
                .orElseThrow({ -> new CustomEntityNotFoundException("Project", name) })
    }

}

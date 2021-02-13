package be.ducobu.inTime.service

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.repository.WorkspaceRepository
import org.springframework.stereotype.Service

@Service
class WorkspaceService {

    private final WorkspaceRepository workspaceRepository

    WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository
    }

    Workspace save(Workspace workspace) {
        return workspaceRepository.save(workspace)
    }

    Workspace findById(Long id) {
        return workspaceRepository.findById(id)
                .orElseThrow({ -> new CustomEntityNotFoundException("Workspace", id as String) })
    }

    Workspace findByName(String name) {
        return workspaceRepository.findByName(name)
                .orElseThrow({ -> new CustomEntityNotFoundException("Workspace", name) })
    }

}

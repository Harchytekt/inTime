package be.ducobu.inTime.repository

import be.ducobu.inTime.model.Workspace
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

}

package be.ducobu.inTime.repository

import be.ducobu.inTime.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository extends JpaRepository<Project, Long> {

}

package be.ducobu.inTime.repository

import be.ducobu.inTime.model.TimeEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {

    Optional<TimeEntry> findByTogglId(Long togglId)

    TimeEntry findByRunningTrue()

    List<TimeEntry> findAllByOrderByIdDesc()

}

package be.ducobu.inTime.repository

import be.ducobu.inTime.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByName(String name)

}

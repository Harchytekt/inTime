package be.ducobu.inTime.service

import be.ducobu.inTime.exception.CustomEntityNotFoundException
import be.ducobu.inTime.model.Client
import be.ducobu.inTime.model.Workspace
import be.ducobu.inTime.repository.ClientRepository
import be.ducobu.inTime.repository.WorkspaceRepository
import org.springframework.stereotype.Service

@Service
class ClientService {

    private final ClientRepository clientRepository

    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository
    }

    Long save(Client client) {
        return clientRepository.save(client).id
    }

    Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow({ -> new CustomEntityNotFoundException("Client", id as String) })
    }

    Client findByName(String name) {
        return clientRepository.findByName(name)
                .orElseThrow({ -> new CustomEntityNotFoundException("Client", name) })
    }

}

package org.cross.service;

import java.util.List;
import java.util.UUID;
import org.cross.api.dto.ClientAddDto;
import org.cross.api.dto.ClientUpdateDto;
import org.cross.model.Client;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    List<Client> getClients();

    List<Client> getClientsPageable(Pageable pageable);

    Client findById(UUID clientID);

    Client addNewClient(ClientAddDto clientAddDto);

    Boolean deleteClient(UUID clientId);

    Client updateClient(UUID clientId, ClientUpdateDto clientUpdateDto);
}

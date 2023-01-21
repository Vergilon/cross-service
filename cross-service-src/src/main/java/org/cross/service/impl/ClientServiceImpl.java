package org.cross.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cross.api.dto.ClientAddDto;
import org.cross.api.dto.ClientUpdateDto;
import org.cross.exception.ClientNotFoundException;
import org.cross.mapper.ClientMapper;
import org.cross.model.Client;
import org.cross.repository.ClientRepository;
import org.cross.service.ClientService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getClientsPageable(Pageable pageable) {
        return clientRepository.findAll(pageable)
            .stream()
            .collect(toList());
    }

    @Override
    public Client findById(UUID clientId) {
        return clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %s no found", clientId)));
    }

    @Override
    public Client addNewClient(ClientAddDto clientAddDto) {
        return clientRepository.save(clientMapper.addDtoToEntity(clientAddDto));
    }

    @Override
    public Boolean deleteClient(UUID clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new IllegalStateException("client id " + clientId + "does not exist");
        }
        clientRepository.deleteById(clientId);
        return true;
    }

    @Override
    public Client updateClient(UUID clientId, ClientUpdateDto clientUpdateDto) {
        var optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isEmpty()) {
            return null;
        }
        var client = optionalClient.get();
        client.setAge(clientUpdateDto.getAge() == null ? client.getAge() : clientUpdateDto.getAge());
        client.setFullName(clientUpdateDto.getFullName() == null ? client.getFullName() : clientUpdateDto.getFullName());
        client.setEmail(clientUpdateDto.getEmail() == null ? client.getEmail() : clientUpdateDto.getEmail());
        return clientRepository.save(client);
    }
}

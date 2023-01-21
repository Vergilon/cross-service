package org.cross.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cross.api.dto.ClientAddDto;
import org.cross.api.dto.ClientFullDto;
import org.cross.api.dto.ClientUpdateDto;
import org.cross.api.dto.FilterDto;
import org.cross.mapper.ClientMapper;
import org.cross.service.ClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public List<ClientFullDto> getClients() {
        return clientService.getClients()
            .stream()
            .map(clientMapper::toFullDto)
            .collect(toList());
    }

    @PostMapping(path = ":filter")
    public List<ClientFullDto> getClientsPageable(@RequestBody FilterDto filterDto) {
        return clientService.getClientsPageable(PageRequest.of(filterDto.getNumber(), filterDto.getSize()))
            .stream()
            .map(clientMapper::toFullDto)
            .collect(toList());
    }

    @GetMapping(path = "{clientId}")
    public ClientFullDto findById(@PathVariable("clientId") UUID clientID) {
        return clientMapper.toFullDto(clientService.findById(clientID));
    }

    @PostMapping
    public ClientFullDto addNewClient(@RequestBody ClientAddDto client) {
        return clientMapper.toFullDto(clientService.addNewClient(client));
    }

    @DeleteMapping(path = "{clientId}")
    public Boolean deleteClient(@PathVariable("clientId") UUID clientId) {
        return clientService.deleteClient(clientId);
    }

    @PutMapping(path = "{clientId}")
    public ClientFullDto updateClient(
        @PathVariable("clientId") UUID clientId,
        @RequestBody ClientUpdateDto clientUpdateDto) {
        return clientMapper.toFullDto(clientService.updateClient(clientId, clientUpdateDto));
    }
}
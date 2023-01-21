package org.cross.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cross.api.dto.ShoesAddDto;
import org.cross.api.dto.ShoesFullDto;
import org.cross.api.dto.ShoesUpdateDto;
import org.cross.mapper.ShoesMapper;
import org.cross.model.Shoes;
import org.cross.repository.ShoesRepository;
import org.cross.service.ClientService;
import org.cross.service.ShoesService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoesServiceImpl implements ShoesService {

    private final ClientService clientService;
    private final ShoesRepository shoesRepository;
    private final ShoesMapper shoesMapper;

    @Override
    public List<ShoesFullDto> getShoes() {
        return shoesRepository.findAll()
            .stream()
            .map(shoesMapper::toFullDto)
            .collect(toList());
    }

    @Override
    public ShoesFullDto findById(UUID shoesID) {
        return shoesRepository.findById(shoesID).map(shoesMapper::toFullDto).orElse(null);
    }

    public ShoesFullDto findByClientId(UUID clientId) {
        return shoesRepository.findByClientId(clientId).map(shoesMapper::toFullDto).orElseThrow(null);
    }

    public ShoesFullDto addNewShoes(ShoesAddDto shoesAddDto) {
        Shoes shoes = shoesMapper.addDtoToEntity(shoesAddDto);
        var client = clientService.findById(shoesAddDto.getClientId());
        shoes.setClient(client);
        var result = shoesRepository.save(shoes);
        return shoesMapper.toFullDto(result);
    }

    @Override
    public Boolean deleteShoes(UUID shoesId) {
        if (!shoesRepository.existsById(shoesId)) {
            throw new IllegalStateException("shoes id " + shoesId + "does not exist");
        }
        shoesRepository.deleteById(shoesId);
        return true;
    }

    @Override
    public ShoesFullDto updateShoes(UUID shoesId, ShoesUpdateDto shoesUpdateDto) {
        var optionalShoes = shoesRepository.findById(shoesId);
        if (optionalShoes.isEmpty()) {
            return null;
        }
        var shoes = optionalShoes.get();
        shoes.setName(shoesUpdateDto.getName() == null ? shoes.getName() : shoesUpdateDto.getName());
        var result = shoesRepository.save(shoes);
        return shoesMapper.toFullDto(result);
    }
}

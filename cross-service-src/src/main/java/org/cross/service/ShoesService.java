package org.cross.service;

import java.util.List;
import java.util.UUID;
import org.cross.api.dto.ShoesAddDto;
import org.cross.api.dto.ShoesFullDto;
import org.cross.api.dto.ShoesUpdateDto;

public interface ShoesService {

    List<ShoesFullDto> getShoes();

    ShoesFullDto findById(UUID shoesId);

    ShoesFullDto findByClientId(UUID clientId);

    ShoesFullDto addNewShoes(ShoesAddDto shoesAddDto);

    Boolean deleteShoes(UUID clientId);

    ShoesFullDto updateShoes(UUID clientId, ShoesUpdateDto shoesUpdateDto);
}

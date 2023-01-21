package org.cross.mapper;

import java.util.List;
import org.cross.api.dto.ShoesAddDto;
import org.cross.api.dto.ShoesFullDto;
import org.cross.model.Shoes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShoesMapper {

    Shoes addDtoToEntity(ShoesAddDto shoesAddDto);

    @Mapping(target = "clientId", source = "shoes.client.id")
    ShoesFullDto toFullDto(Shoes shoes);

    @Mapping(target = "clientId", source = "shoes.client.id")
    List<ShoesFullDto> listShoesFullDto(List<Shoes> shoes);
}

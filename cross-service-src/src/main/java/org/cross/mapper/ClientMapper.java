package org.cross.mapper;

import org.cross.api.dto.ClientAddDto;
import org.cross.api.dto.ClientFullDto;
import org.cross.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ShoesMapper.class})
public interface ClientMapper {

    Client addDtoToEntity(ClientAddDto clientAddDto);

    @Mapping(target = "shoes", source = "client.shoes")
    ClientFullDto toFullDto(Client client);
}

package org.cross.api.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cross.api.enums.ShoesType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoesAddDto {
    private String name;
    private ShoesType shoesType;
    private UUID clientId;
}
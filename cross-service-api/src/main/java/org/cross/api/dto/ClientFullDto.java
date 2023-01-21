package org.cross.api.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientFullDto {
    private UUID id;
    private String fullName;
    private String email;
    private Integer age;
    private List<ShoesFullDto> shoes;
}
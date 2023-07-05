package de.adesso.trmdeamon.dto.attributes;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructAttributesDto {

    @NotNull
    private List<ConstructAttributeDto> constructAttributeDtos;
}

package de.adesso.trmdeamon.dto.attributes;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructAttributeDto {

    @NotNull
    private String name;

    @NotNull
    private String value;
}

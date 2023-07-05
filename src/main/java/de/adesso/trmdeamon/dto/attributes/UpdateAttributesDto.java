package de.adesso.trmdeamon.dto.attributes;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAttributesDto {

    private String name;

    private String value;
}

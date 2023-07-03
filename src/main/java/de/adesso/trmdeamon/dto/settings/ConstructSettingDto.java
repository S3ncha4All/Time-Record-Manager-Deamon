package de.adesso.trmdeamon.dto.settings;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructSettingDto {

    @NotNull
    private String name;

    @NotNull
    private String value;

}

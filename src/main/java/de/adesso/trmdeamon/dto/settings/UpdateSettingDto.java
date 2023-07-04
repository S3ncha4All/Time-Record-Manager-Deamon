package de.adesso.trmdeamon.dto.settings;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSettingDto {

    private String name;

    private String value;

}

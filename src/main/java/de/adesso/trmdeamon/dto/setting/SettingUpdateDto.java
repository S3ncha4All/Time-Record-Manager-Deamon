package de.adesso.trmdeamon.dto.setting;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingUpdateDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String value;


}

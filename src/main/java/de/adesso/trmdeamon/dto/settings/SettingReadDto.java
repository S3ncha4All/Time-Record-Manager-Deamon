package de.adesso.trmdeamon.dto.settings;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingReadDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String value;

    @NotNull
    private Long timeSheetId;

}

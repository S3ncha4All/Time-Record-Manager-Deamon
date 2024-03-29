package de.adesso.trmdeamon.dto.setting;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingCreateDto {

    @NotNull
    private String name;

    private String value;

    @NotNull
    private Long timeSheetId;

}

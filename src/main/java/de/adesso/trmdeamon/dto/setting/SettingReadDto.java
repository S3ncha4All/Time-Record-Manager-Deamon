package de.adesso.trmdeamon.dto.setting;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("time-sheet-id")
    private Long timeSheetId;

}

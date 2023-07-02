package de.adesso.trmdeamon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {

    private Long id;

    private String name;

    private String value;

    @NotNull
    @JsonProperty("time_sheet_id")
    private Long timeSheetId;
}

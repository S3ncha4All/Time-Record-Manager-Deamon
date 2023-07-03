package de.adesso.trmdeamon.dto.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String value;

}

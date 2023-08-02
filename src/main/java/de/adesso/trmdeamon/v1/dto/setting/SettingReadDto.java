package de.adesso.trmdeamon.v1.dto.setting;

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

}

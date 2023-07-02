package de.adesso.trmdeamon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetDto {

    private Long id;

    @NotNull
    private String name;
}

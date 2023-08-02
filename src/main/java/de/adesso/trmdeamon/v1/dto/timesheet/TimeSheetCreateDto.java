package de.adesso.trmdeamon.v1.dto.timesheet;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetCreateDto {

    @NotNull
    private String name;
}

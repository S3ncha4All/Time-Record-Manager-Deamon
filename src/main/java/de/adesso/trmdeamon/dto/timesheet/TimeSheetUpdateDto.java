package de.adesso.trmdeamon.dto.timesheet;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetUpdateDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}

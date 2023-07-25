package de.adesso.trmdeamon.dto.timesheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetReadDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @JsonProperty(value = "booking_count", defaultValue = "0")
    private int bookingCount;
}

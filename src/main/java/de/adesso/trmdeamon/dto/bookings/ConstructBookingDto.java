package de.adesso.trmdeamon.dto.bookings;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructBookingDto {

    @NotNull
    private String name;
}

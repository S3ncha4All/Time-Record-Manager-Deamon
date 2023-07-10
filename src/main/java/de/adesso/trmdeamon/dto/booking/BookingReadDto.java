package de.adesso.trmdeamon.dto.booking;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingReadDto {

    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime begin;

    private LocalDateTime end;

}

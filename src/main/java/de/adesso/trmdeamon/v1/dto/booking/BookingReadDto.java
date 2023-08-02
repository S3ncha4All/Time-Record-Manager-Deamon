package de.adesso.trmdeamon.v1.dto.booking;


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

    private LocalDateTime begin;

    private LocalDateTime end;

}

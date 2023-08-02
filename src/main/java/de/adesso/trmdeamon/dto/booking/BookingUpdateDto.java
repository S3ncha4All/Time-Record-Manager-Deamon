package de.adesso.trmdeamon.dto.booking;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingUpdateDto {

    @NotNull
    private Long id;

    private LocalDateTime begin;

    private LocalDateTime end;

}

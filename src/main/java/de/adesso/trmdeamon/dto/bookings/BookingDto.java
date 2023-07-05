package de.adesso.trmdeamon.dto.bookings;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    @NotNull
    private Long id;

    @NotNull
    private Long bucketId;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime begin;

    private LocalDateTime end;
}

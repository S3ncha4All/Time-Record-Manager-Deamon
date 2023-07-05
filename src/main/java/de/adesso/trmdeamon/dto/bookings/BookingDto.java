package de.adesso.trmdeamon.dto.bookings;


import de.adesso.trmdeamon.dto.attributes.AttributesDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime begin;

    private LocalDateTime end;

    @NotNull
    private List<AttributesDto> attributes;
}

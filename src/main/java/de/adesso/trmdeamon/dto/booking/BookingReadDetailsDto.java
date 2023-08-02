package de.adesso.trmdeamon.dto.booking;


import de.adesso.trmdeamon.dto.tag.TagReadDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingReadDetailsDto {

    @NotNull
    private Long id;

    private LocalDateTime begin;

    private LocalDateTime end;

    private List<TagReadDto> tags;


}

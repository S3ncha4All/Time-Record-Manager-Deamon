package de.adesso.trmdeamon.dto.booking;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.adesso.trmdeamon.dto.TagDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingReadDto {

    @NotNull
    private Long id;

    @NotNull
    @JsonProperty("time_sheet_id")
    private Long timeSheetId;

    @NotNull
    private LocalDateTime begin;

    private LocalDateTime end;

}

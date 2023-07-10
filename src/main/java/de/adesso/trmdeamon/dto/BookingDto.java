package de.adesso.trmdeamon.dto;


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
public class BookingDto {

    private Long id;

    @NotNull
    @JsonProperty("time_sheet_id")
    private Long timeSheetId;

    private LocalDateTime begin;

    private LocalDateTime end;

    private List<TagDto> tags;


}

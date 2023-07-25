package de.adesso.trmdeamon.dto.booking;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.adesso.trmdeamon.dto.bookingtags.BookingTagsDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateDto {

    @NotNull
    @JsonProperty("time_sheet_id")
    private Long timeSheetId;

    @JsonProperty("active_right_away")
    private Boolean activateRightAway;

    @JsonProperty("tags")
    private BookingTagsDto tagsDto;

}

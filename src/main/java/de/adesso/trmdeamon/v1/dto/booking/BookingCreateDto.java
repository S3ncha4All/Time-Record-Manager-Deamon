package de.adesso.trmdeamon.v1.dto.booking;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.adesso.trmdeamon.v1.dto.bookingtags.BookingTagsDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

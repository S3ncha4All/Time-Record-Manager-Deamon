package de.adesso.trmdeamon.dto.bookingtags;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingTagsDto {

    @JsonProperty("list_tag_id")
    private List<Long> tagIds;

    @JsonProperty("list_tag_name")
    private List<String> tagNames;
}

package de.adesso.trmdeamon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {

    private Long id;

    private String name;

    @JsonProperty("parent_id")
    private Long parentBucketId;

    @JsonProperty("time_sheet_id")
    private Long timeSheetId;
}

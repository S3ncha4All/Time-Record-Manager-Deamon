package de.adesso.trmdeamon.dto.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructBucketDto {

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("parent_id")
    private Long parentBucketId;
}

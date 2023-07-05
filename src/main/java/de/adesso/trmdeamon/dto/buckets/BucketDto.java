package de.adesso.trmdeamon.dto.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.adesso.trmdeamon.dto.attributes.AttributesDto;
import de.adesso.trmdeamon.dto.bookings.BookingDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<BucketDto> childBuckets;

    @NotNull
    private List<BookingDto> bookings;

    @NotNull
    private List<AttributesDto> attributes;

}

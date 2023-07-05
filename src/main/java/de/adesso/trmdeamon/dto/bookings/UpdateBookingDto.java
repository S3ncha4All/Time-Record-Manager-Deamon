package de.adesso.trmdeamon.dto.bookings;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingDto {

    @JsonProperty("bucket_id")
    private Long bucketId;

    private String name;

    @JsonProperty("end_booking")
    private Boolean endBooking;
}

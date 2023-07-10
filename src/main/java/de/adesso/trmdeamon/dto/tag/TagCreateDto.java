package de.adesso.trmdeamon.dto.tag;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagCreateDto {

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("booking-id")
    private Long bookingId;

}

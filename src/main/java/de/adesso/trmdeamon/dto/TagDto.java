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
public class TagDto {

    private Long id;

    @NotNull
    private String name;

    private Long bookingId;

}

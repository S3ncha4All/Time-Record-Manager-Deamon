package de.adesso.trmdeamon.v1.dto.tag;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagReadDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;
}

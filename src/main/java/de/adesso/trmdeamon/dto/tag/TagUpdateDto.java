package de.adesso.trmdeamon.dto.tag;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagUpdateDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}

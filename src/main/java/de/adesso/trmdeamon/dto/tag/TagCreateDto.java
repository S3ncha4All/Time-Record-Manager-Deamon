package de.adesso.trmdeamon.dto.tag;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagCreateDto {

    @NotNull
    private String tagName;

}

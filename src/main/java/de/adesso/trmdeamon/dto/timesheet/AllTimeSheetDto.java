package de.adesso.trmdeamon.dto.timesheet;

import de.adesso.trmdeamon.dto.buckets.BucketDto;
import de.adesso.trmdeamon.dto.settings.SettingDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllTimeSheetDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}

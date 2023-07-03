package de.adesso.trmdeamon.dto.timesheet;

import de.adesso.trmdeamon.dto.BucketDto;
import de.adesso.trmdeamon.dto.settings.SettingDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<SettingDto> settings;

    @NotNull
    private List<BucketDto> buckets;

}

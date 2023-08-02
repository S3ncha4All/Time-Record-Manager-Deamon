package de.adesso.trmdeamon.v1.dto.timesheet;

import de.adesso.trmdeamon.v1.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.v1.dto.setting.SettingReadDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetReadDetailsDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private List<SettingReadDto> settings;

    private List<BookingReadDetailsDto> bookings;

}

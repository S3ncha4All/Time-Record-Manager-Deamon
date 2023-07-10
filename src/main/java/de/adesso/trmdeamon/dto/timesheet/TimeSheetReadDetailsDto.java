package de.adesso.trmdeamon.dto.timesheet;

import de.adesso.trmdeamon.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.dto.settings.SettingReadDto;
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

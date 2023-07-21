package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.model.TimeSheet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;


@ExtendWith(MockitoExtension.class)
public class TimeSheetUnitTest {

    @Spy
    private TagMapper tagMapper = new TagMapper();
    @Spy
    private BookingMapper bookingMapper = new BookingMapper(tagMapper);
    @Spy
    private SettingsMapper settingsMapper = new SettingsMapper();

    @InjectMocks
    private TimeSheetMapper cut;

    @Test
    public void testToReadDto() {
        Long id = 1234L;
        String name = "name";
        TimeSheet ts = TimeSheet.builder()
                .id(id)
                .name(name)
                .bookings(new ArrayList<>())
                .settings(new ArrayList<>())
                .build();
        TimeSheetReadDto dto = cut.toReadDto(ts);
        MatcherAssert.assertThat(dto, Matchers.allOf(
                Matchers.hasProperty("id", equalTo(id)),
                Matchers.hasProperty("name", equalTo(name))
        ));
    }
}

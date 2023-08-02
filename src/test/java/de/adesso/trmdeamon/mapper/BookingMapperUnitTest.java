package de.adesso.trmdeamon.mapper;


import de.adesso.trmdeamon.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import de.adesso.trmdeamon.model.Tag;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class BookingMapperUnitTest {

    @Spy
    private TagMapper tagMapper = new TagMapper();
    @InjectMocks
    private BookingMapper cut; //Class Under Test (cut)

    @Test
    public void testToReadDto() {
        Long id = 1234L;
        LocalDateTime begin = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        Booking b = Booking.builder()
                .id(id)
                .begin(begin)
                .end(end)
                .build();
        BookingReadDto dto = cut.toReadDto(b);
        MatcherAssert.assertThat(dto, expectedBookingDto(id, begin, end));
    }

    private static Matcher<BookingReadDto> expectedBookingDto(Long id, LocalDateTime begin, LocalDateTime end) {
        return Matchers.allOf(
                Matchers.hasProperty("id", equalTo(id)),
                Matchers.hasProperty("begin", equalTo(begin)),
                Matchers.hasProperty("end", equalTo(end))
        );
    }

    @Test
    public void testToReadDetailsDto() {
        Long id = 1234L;
        LocalDateTime begin = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        List<BookingTags> tags = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
                tags.add(BookingTags.builder()
                        .tag(
                                Tag.builder()
                                        .id((long) i)
                                        .name("Name"+i)
                                        .build())
                        .build());
        }
        Booking b = Booking.builder()
                .id(id)
                .begin(begin)
                .end(end)
                .tags(tags)
                .build();
        BookingReadDetailsDto dto = cut.toReadDetailsDto(b);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(begin, dto.getBegin());
        Assertions.assertEquals(end, dto.getEnd());
        Long index = 2L;
        Assertions.assertEquals(index, dto.getTags().get(index.intValue()).getId());
        Assertions.assertEquals("Name"+index, dto.getTags().get(index.intValue()).getName());
    }
}

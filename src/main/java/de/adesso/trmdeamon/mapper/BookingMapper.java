package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final TagMapper tagMapper;

    public BookingReadDto toReadDto(Booking booking) {
        return BookingReadDto.builder()
                .id(booking.getId())
                .begin(booking.getBegin())
                .end(booking.getEnd())
                .build();
    }

    public BookingReadDetailsDto toReadDetailsDto(Booking booking) {
        BookingReadDetailsDto b =  BookingReadDetailsDto.builder()
                .id(booking.getId())
                .begin(booking.getBegin())
                .end(booking.getEnd())
                .build();
        if(booking.getTags() != null) {
            b.setTags(tagMapper.listFromEntity(booking.getTags().stream().map(BookingTags::getTag).toList()));
        } else {
            b.setTags(new ArrayList<>());
        }
        return b;
    }

    public List<BookingReadDto> listToReadDto(List<Booking> list) {
        return list.stream().map(this::toReadDto).toList();
    }

    public List<BookingReadDetailsDto> listToReadDetailsDto(List<Booking> list) {
        return list.stream().map(this::toReadDetailsDto).toList();
    }

    public Page<BookingReadDto> pageToReadDto(Page<Booking> page) {
        return page.map(this::toReadDto);
    }
}

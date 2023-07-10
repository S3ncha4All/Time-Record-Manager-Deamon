package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.BookingDto;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final TagMapper tagMapper;

    public Booking fromDto(BookingDto dto) {
        return Booking.builder()
                .id(dto.getId())
                .begin(dto.getBegin())
                .end(dto.getEnd())
                .build();
    }

    public BookingDto fromEntity(Booking booking) {
        BookingDto b =  BookingDto.builder()
                .id(booking.getId())
                .begin(booking.getBegin())
                .end(booking.getEnd())
                .timeSheetId(booking.getTimeSheet().getId())
                .build();
        if(booking.getTags() != null) {
            b.setTags(tagMapper.listFromEntity(booking.getTags().stream().map(BookingTags::getTag).toList()));
        } else {
            b.setTags(new ArrayList<>());
        }
        return b;
    }

    public List<BookingDto> listFromEntity(List<Booking> list) {
        return list.stream().map(this::fromEntity).toList();
    }
}

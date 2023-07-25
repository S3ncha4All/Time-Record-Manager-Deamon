package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.booking.BookingCreateDto;
import de.adesso.trmdeamon.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.dto.booking.BookingUpdateDto;
import de.adesso.trmdeamon.mapper.BookingMapper;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import de.adesso.trmdeamon.model.Tag;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.BookingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingMapper mapper;
    private final BookingsRepository repository;
    private final BookingTagService bookingTagService;
    private final TimeSheetService timeSheetService;

    public BookingReadDto createBooking(BookingCreateDto dto) {
        Booking b = Booking.builder().build();
        TimeSheet ts = timeSheetService.getTimeSheet(dto.getTimeSheetId());
        b.setTimeSheet(ts);
        if(Boolean.TRUE.equals(dto.getActivateRightAway())) {
            b.setBegin(LocalDateTime.now());
        }
        Booking saved = repository.save(b);
        if(dto.getTagsDto() != null) {

        }
        return mapper.toReadDto(saved);
    }

    public BookingReadDto startBooking(Long id) {
        Booking b = getBooking(id);
        if(b.getBegin() != null) throw new RuntimeException("Booking with ID already started!");
        b.setBegin(LocalDateTime.now());
        return mapper.toReadDto(repository.save(b));
    }

    public BookingReadDto endBooking(Long id) {
        Booking b = getBooking(id);
        if(b.getEnd() != null) throw new RuntimeException("Booking with ID already ended!");
        b.setEnd(LocalDateTime.now());
        return mapper.toReadDto(repository.save(b));
    }

    public BookingReadDto updateBooking(BookingUpdateDto dto) {
        Booking b = getBooking(dto.getId());
        if(dto.getBegin() != null) {
            b.setBegin(dto.getBegin());
        }
        if(dto.getEnd() != null) {
            b.setEnd(dto.getEnd());
        }
        return mapper.toReadDto(repository.save(b));
    }

    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }

    public BookingReadDetailsDto getBookingDto(Long id) {
        return mapper.toReadDetailsDto(getBooking(id));
    }

    public List<BookingReadDto> getAllBooking(Long timeSheetId) {
        if(timeSheetId != null) {
            return mapper.listToReadDto(repository.findAllBookingsForTimeSheetId(timeSheetId));
        } else {
            return mapper.listToReadDto(repository.findAll());
        }
    }

    public Booking getBooking(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Booking not found")
        );
    }
}

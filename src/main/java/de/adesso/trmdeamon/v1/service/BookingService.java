package de.adesso.trmdeamon.v1.service;

import de.adesso.trmdeamon.v1.dto.booking.BookingCreateDto;
import de.adesso.trmdeamon.v1.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.v1.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.v1.dto.booking.BookingUpdateDto;
import de.adesso.trmdeamon.v1.mapper.BookingMapper;
import de.adesso.trmdeamon.v1.model.Booking;
import de.adesso.trmdeamon.v1.model.TimeSheet;
import de.adesso.trmdeamon.v1.repository.BookingsRepository;
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

    public BookingReadDetailsDto createBooking(BookingCreateDto dto) {
        Booking b = Booking.builder().build();
        TimeSheet ts = timeSheetService.getTimeSheet(dto.getTimeSheetId());
        b.setTimeSheet(ts);
        if(Boolean.TRUE.equals(dto.getActivateRightAway())) {
            b.setBegin(LocalDateTime.now());
        }
        Booking saved = repository.save(b);
        if(dto.getTagsDto() != null) {
            bookingTagService.addTagsToBooking(saved.getId(), dto.getTagsDto());
        }
        return mapper.toReadDetailsDto(saved);
    }

    public BookingReadDto startBooking(Long id) {
        Booking b = getBooking(id);
        if(b.getBegin() != null) throw new RuntimeException("Booking with ID already started!");
        b.setBegin(LocalDateTime.now());
        return mapper.toReadDto(repository.save(b));
    }

    public BookingReadDto endBooking(Long id) {
        Booking b = getBooking(id);
        if(b.getBegin() == null) throw new RuntimeException("Booking with ID did not start!");
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

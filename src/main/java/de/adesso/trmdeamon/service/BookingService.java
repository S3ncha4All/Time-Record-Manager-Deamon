package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.BookingDto;
import de.adesso.trmdeamon.mapper.BookingMapper;
import de.adesso.trmdeamon.model.Booking;
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
    private final TimeSheetService timeSheetService;

    public BookingDto createBooking(BookingDto dto) {
        Booking b = mapper.fromDto(dto);
        TimeSheet ts = timeSheetService.getTimeSheet(dto.getTimeSheetId());
        b.setTimeSheet(ts);
        b.setBegin(LocalDateTime.now());
        return mapper.fromEntity(repository.save(b));
    }

    public BookingDto updateBooking(BookingDto dto) {
        Booking b = getBooking(dto.getId());
        if(dto.getBegin() != null) {
            b.setBegin(dto.getBegin());
        }
        if(dto.getEnd() != null) {
            b.setEnd(dto.getEnd());
        }
        return mapper.fromEntity(repository.save(b));
    }

    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }

    public BookingDto getBookingDto(Long id) {
        return mapper.fromEntity(getBooking(id));
    }

    public List<BookingDto> getAllBooking() {
        return mapper.listFromEntity(repository.findAll());
    }

    public Booking getBooking(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Booking not found")
        );
    }
}

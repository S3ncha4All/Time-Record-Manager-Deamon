package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.mapper.BookingMapper;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.repository.BookingsRepository;
import de.adesso.trmdeamon.util.MakePageRequest;
import de.adesso.trmdeamon.util.sort.SortBookings;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.dto.booking.BookingReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceV2 {

    private final BookingMapper mapper;
    private final BookingsRepository repository;

    public Page<BookingReadDto> getPagedBooking(Long timeSheetId, Integer pageIndex, Integer pageSize, SortBookings sortBookings, SortOrder sortOrder) {
        Pageable pr = MakePageRequest.make(pageIndex, pageSize, sortBookings, sortOrder);
        Page<Booking> list;
        if(timeSheetId != null) {
            list = repository.findAllBookingsForTimeSheetId(timeSheetId, pr);
        } else {
            list = repository.findAll(pr);
        }
        return mapper.pageToReadDto(list);
    }
}

package de.adesso.trmdeamon.v2.service;

import de.adesso.trmdeamon.util.sort.SortBookings;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.v1.dto.booking.BookingReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceV2 {

    public Page<BookingReadDto> getPagedBooking(Long timeSheetId, Integer pageIndex, Integer pageSize, SortBookings sortBookings, SortOrder sortOrder) {
        return null;
    }
}

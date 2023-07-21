package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.bookingtags.BookingTagsDto;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import de.adesso.trmdeamon.model.Tag;
import de.adesso.trmdeamon.repository.BookingTagsRepository;
import de.adesso.trmdeamon.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingTagService {

    private final BookingService bookingService;
    private final TagsRepository tagsRepository;
    private final BookingTagsRepository repository;
    public void addTagsToBooking(Long bookingId, BookingTagsDto dto) {
        Booking b = bookingService.getBooking(bookingId);
        if(dto.getTagIds() != null && !dto.getTagIds().isEmpty()) {
            List<Tag> tagsFromIds = tagsRepository.findAllById(dto.getTagIds());
            List<BookingTags> bookingTags = tagsFromIds.stream().map(t -> BookingTags.builder().tag(t).booking(b).build()).toList();
            repository.saveAll(bookingTags);
        }
        if(dto.getTagNames() != null && !dto.getTagNames().isEmpty()) {
            List<Tag> tagsFromNames = tagsRepository.findAllByName(dto.getTagNames());
            List<BookingTags> bookingTags = tagsFromNames.stream().map(t -> BookingTags.builder().tag(t).booking(b).build()).toList();
            repository.saveAll(bookingTags);
        }
    }

    public void removeTagsFromBooking(Long bookingId, BookingTagsDto dto) {
        Booking b = bookingService.getBooking(bookingId);
        repository.deleteByBookingIdAndTagId(bookingId, dto.getTagIds());
        repository.deleteByBookingIdAndTagName(bookingId, dto.getTagNames());
    }
}
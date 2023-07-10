package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.TagDto;
import de.adesso.trmdeamon.mapper.TagMapper;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import de.adesso.trmdeamon.model.Tag;
import de.adesso.trmdeamon.repository.BookingTagsRepository;
import de.adesso.trmdeamon.repository.BookingsRepository;
import de.adesso.trmdeamon.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagMapper tagMapper;

    private final TagsRepository tagRepository;
    private final BookingTagsRepository bookingTagsRepository;
    private final BookingsRepository bookingRepository;

    public TagDto createTag(TagDto dto) {
        Tag t = tagMapper.fromDto(dto);
        t = tagRepository.save(t);
        Booking b = getBooking(dto.getBookingId());
        BookingTags bt = BookingTags.builder()
                .tag(t)
                .booking(b)
                .build();
        bookingTagsRepository.save(bt);
        return tagMapper.fromEntity(t);
    }

    public List<TagDto> getAllTags(Long bookingId) {
        if(bookingId == null) {
            return tagMapper.listFromEntity(tagRepository.findAll());
        } else {
            Booking b = getBooking(bookingId);
            return tagMapper.listFromEntity(b.getTags().stream().map(BookingTags::getTag).toList());
        }
    }

    private Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Booking not found")
        );
    }

    public TagDto updateTag(TagDto dto) {
        Tag t = tagRepository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("Tag not found")
        );
        t.setName(dto.getName());
        return tagMapper.fromEntity(tagRepository.save(t));
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}

package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.tag.TagCreateDto;
import de.adesso.trmdeamon.dto.tag.TagReadDto;
import de.adesso.trmdeamon.dto.tag.TagUpdateDto;
import de.adesso.trmdeamon.mapper.TagMapper;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import de.adesso.trmdeamon.model.Tag;
import de.adesso.trmdeamon.repository.BookingTagsRepository;
import de.adesso.trmdeamon.repository.BookingsRepository;
import de.adesso.trmdeamon.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagMapper tagMapper;

    private final TagsRepository tagRepository;
    private final BookingTagsRepository bookingTagsRepository;
    private final BookingsRepository bookingRepository;

    public List<TagReadDto> createTag(TagCreateDto dto) {
        Booking b = getBooking(dto.getBookingId());
        List<Tag> tags = tagRepository.saveAll(dto.getTagNames().stream().map(n -> Tag.builder().name(n).build()).toList());
        List<BookingTags> bookingTags = tags.stream().map(t -> BookingTags.builder().tag(t).booking(b).build()).toList();
        List<BookingTags> bts = bookingTagsRepository.saveAll(bookingTags);
        return tagMapper.listFromEntity(tags);
    }

    public List<TagReadDto> getAllTags(Long bookingId) {
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

    public TagReadDto updateTag(TagUpdateDto dto) {
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

package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.tag.TagCreateDto;
import de.adesso.trmdeamon.dto.tag.TagReadDto;
import de.adesso.trmdeamon.dto.tag.TagUpdateDto;
import de.adesso.trmdeamon.mapper.TagMapper;
import de.adesso.trmdeamon.model.Tag;
import de.adesso.trmdeamon.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagMapper tagMapper;

    private final TagsRepository tagRepository;

    public TagReadDto createTag(TagCreateDto dto) {
        Tag t = Tag.builder()
                .name(dto.getTagName())
                .build();
        return tagMapper.fromEntity(t);
    }

    public List<TagReadDto> getAllTags() {
        return tagMapper.listFromEntity(tagRepository.findAll());
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

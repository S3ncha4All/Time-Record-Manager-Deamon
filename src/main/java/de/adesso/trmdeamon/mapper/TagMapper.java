package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.tag.TagCreateDto;
import de.adesso.trmdeamon.dto.tag.TagReadDto;
import de.adesso.trmdeamon.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagMapper {

    public TagReadDto fromEntity(Tag tag) {
        return TagReadDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public List<TagReadDto> listFromEntity(List<Tag> list) {
        return list.stream().map(this::fromEntity).toList();
    }
}

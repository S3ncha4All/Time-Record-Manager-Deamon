package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.TagDto;
import de.adesso.trmdeamon.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagMapper {

    public Tag fromDto(TagDto dto) {
        return Tag.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public TagDto fromEntity(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public List<TagDto> listFromEntity(List<Tag> list) {
        return list.stream().map(this::fromEntity).toList();
    }
}

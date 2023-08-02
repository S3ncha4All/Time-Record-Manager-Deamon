package de.adesso.trmdeamon.v2.service;

import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.v1.dto.tag.TagCreateDto;
import de.adesso.trmdeamon.v1.dto.tag.TagReadDto;
import de.adesso.trmdeamon.v1.dto.tag.TagUpdateDto;
import de.adesso.trmdeamon.v1.mapper.TagMapper;
import de.adesso.trmdeamon.v1.model.Tag;
import de.adesso.trmdeamon.v1.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceV2 {

    public Page<TagReadDto> getPagedTags(String filterName, Integer pageIndex, Integer pageSize, SortTimeSheets sortTimeSheets, SortOrder sortOrder) {
        return null;
    }
}

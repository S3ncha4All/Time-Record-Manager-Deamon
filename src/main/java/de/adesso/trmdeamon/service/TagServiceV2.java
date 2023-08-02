package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.mapper.TagMapper;
import de.adesso.trmdeamon.model.Tag;
import de.adesso.trmdeamon.repository.TagsRepository;
import de.adesso.trmdeamon.util.MakePageRequest;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTags;
import de.adesso.trmdeamon.dto.tag.TagReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceV2 {

    private final TagMapper mapper;
    private final TagsRepository repository;

    public Page<TagReadDto> getPagedTags(String filterName, Integer pageIndex, Integer pageSize, SortTags sortTags, SortOrder sortOrder) {
        Pageable pr = MakePageRequest.make(pageIndex, pageSize, sortTags, sortOrder);
        Page<Tag> page;
        if(filterName != null && !filterName.isEmpty()) {
            page = repository.findAllByName(filterName, pr);
        } else {
            page = repository.findAll(pr);
        }
        return mapper.pageFromEntity(page);
    }
}

package de.adesso.trmdeamon.util;

import de.adesso.trmdeamon.util.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class MakePageRequest {

    public static Pageable make(Integer pageIndex, Integer pageSize, Enum sortTimeSheets, SortOrder sortOrder) {
        Sort sort = Sort.by(sortTimeSheets.name());
        switch (sortOrder) {
            case asc -> sort.ascending();
            case desc -> sort.descending();
        }
        return PageRequest.of(pageIndex, pageSize, sort);
    }
}

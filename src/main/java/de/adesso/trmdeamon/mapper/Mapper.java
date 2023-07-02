package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.TimeSheetDto;
import de.adesso.trmdeamon.model.TimeSheet;

import java.util.List;

public abstract class Mapper<E, D> {

    public abstract D fromEntity(E e);

    public abstract E fromDto(D dto);

    public final List<D> listFromEntity(List<E> eList) {
        return eList.stream().map(this::fromEntity).toList();
    }

    public final List<E> listFromDto(List<D> dtoList) {
        return dtoList.stream().map(this::fromDto).toList();
    }
}

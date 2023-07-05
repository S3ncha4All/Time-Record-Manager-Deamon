package de.adesso.trmdeamon.mapper;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<E, D> {

    public D fromEntity(E e){
        throw new NotImplementedException();
    }

    public E fromDto(D dto) {
        throw new NotImplementedException();
    }

    public final List<D> listFromEntity(List<E> eList) {
        if(eList == null) return new ArrayList<D>();
        return eList.stream().map(this::fromEntity).toList();
    }

    public final List<E> listFromDto(List<D> dtoList) {
        if(dtoList == null) return new ArrayList<E>();
        return dtoList.stream().map(this::fromDto).toList();
    }
}

package com.maplr.sugarshack.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E, D> {

    @Autowired
    private ModelMapper modelMapper;

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public AbstractMapper() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.dtoClass = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    public List<D> toDtos(List<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Set<D> toDtos(Set<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public List<E> toEntities(List<D> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public Set<E> toEntities(Set<D> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public E toEntity(D dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, entityClass);
    }

    public D toDto(E entity) {
        if (entity == null) return null;
        return modelMapper.map(entity, dtoClass);
    }
}


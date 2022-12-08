package ru.skillbox.mapper;

public interface AbstractMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);
}

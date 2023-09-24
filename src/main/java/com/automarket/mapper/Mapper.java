package com.automarket.mapper;

public interface Mapper<T, TDto> {

    T mapFromDto(TDto dto);
    TDto mapToDto(T obj);
}

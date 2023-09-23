package com.automarket.mapper;

public interface Mapper<T, TDto> {

    public T mapFromDto(TDto dto);
    public TDto mapToDto(T obj);
}

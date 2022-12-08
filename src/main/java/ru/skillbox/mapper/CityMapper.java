package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.dto.CityDto;
import ru.skillbox.model.City;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper extends AbstractMapper<CityDto, City> {
}

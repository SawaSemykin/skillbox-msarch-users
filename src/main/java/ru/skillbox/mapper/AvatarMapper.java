package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.dto.AvatarDto;
import ru.skillbox.model.Avatar;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AvatarMapper extends AbstractMapper<AvatarDto, Avatar> {
}

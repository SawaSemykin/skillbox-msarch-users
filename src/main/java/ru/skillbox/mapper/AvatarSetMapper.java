package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.dto.AvatarDto;
import ru.skillbox.model.Avatar;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { AvatarMapper.class }
)
public interface AvatarSetMapper extends AbstractMapper<Set<AvatarDto>, Set<Avatar>> {
}

package ru.skillbox.mapper;

import org.mapstruct.*;
import ru.skillbox.dto.UserDto;
import ru.skillbox.model.User;

@Named("UserMapper")
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CityMapper.class, HardSkillSetMapper.class, AvatarSetMapper.class }
)
public interface UserMapper extends AbstractMapper<UserDto, User> {
}

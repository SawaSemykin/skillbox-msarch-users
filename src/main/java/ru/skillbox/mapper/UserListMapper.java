package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.dto.UserDto;
import ru.skillbox.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { UserMapper.class }
)
public interface UserListMapper extends AbstractMapper<List<UserDto>, List<User>> {
}

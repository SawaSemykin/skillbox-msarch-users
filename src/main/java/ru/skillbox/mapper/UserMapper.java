package ru.skillbox.mapper;

import org.mapstruct.*;
import ru.skillbox.dto.UserDto;
import ru.skillbox.model.User;

import java.util.Set;

@Named("UserMapper")
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CityMapper.class, HardSkillSetMapper.class, AvatarSetMapper.class },
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserMapper extends AbstractMapper<UserDto, User> {

    @Override
    @Mapping(target = "subscriptions", qualifiedByName = "toEntityWithoutSubscriptions")
    @Mapping(target = "subscribers", qualifiedByName = "toEntityWithoutSubscribers")
    User toEntity(UserDto dto);

    @Named("toEntityWithoutSubscriptions")
    @Mapping(target = "subscriptions", expression = "java(null)")
    Set<User> toEntityWithoutSubscriptions(Set<UserDto> dto);

    @Named("toEntityWithoutSubscribers")
    @Mapping(target = "subscribers", expression = "java(null)")
    Set<User> toEntityWithoutSubscribers(Set<UserDto> dto);
}

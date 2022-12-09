package ru.skillbox.service;

import org.springframework.validation.annotation.Validated;
import ru.skillbox.dto.UserDto;
import ru.skillbox.validation.OnCreateUser;
import ru.skillbox.validation.OnUpdateUser;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface UserService {
    @Validated(OnCreateUser.class)
    long createUser(@Valid UserDto user);

    @Validated(OnUpdateUser.class)
    long updateUser(@Valid UserDto user, Long id);

    long deleteUser(Long id);

    UserDto getUser(Long id);

    List<UserDto> getUsers();

    void subscribe(long id, long subscriptionId);

    void unsubscribe(long id, long subscriptionId);

    List<UserDto> getSubscriptions(Long id);

    List<UserDto> getSubscribers(Long id);
}

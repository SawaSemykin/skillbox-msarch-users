package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.UserDto;
import ru.skillbox.mapper.AbstractMapper;
import ru.skillbox.model.User;
import ru.skillbox.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AbstractMapper<UserDto, User> userMapper;

    private final AbstractMapper<List<UserDto>, List<User>> userListMapper;

    @PostMapping
    public long createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userService.createUser(user);
    }

    @PutMapping(path = "/{id}")
    public long updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        User user = userMapper.toEntity(userDto);
        return userService.updateUser(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public long deleteUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        User user = userMapper.toEntity(userDto);
        return userService.deleteUser(user, id);
    }

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return userMapper.toDto(user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        List<User> users = userService.getUsers();
        return userListMapper.toDto(users);
    }

    @PostMapping(path = "/{id}/subscriptions/{subscriptionId}")
    public void subscribe(@PathVariable(name = "id") long id, @PathVariable(name = "subscriptionId") long subscriptionId) {
        userService.subscribe(id, subscriptionId);

    }

    @DeleteMapping(path = "/{id}/subscriptions/{subscriptionId}")
    public void unsubscribe(@PathVariable(name = "id") long id, @PathVariable(name = "subscriptionId") long subscriptionId) {
        userService.unsubscribe(id, subscriptionId);

    }
}

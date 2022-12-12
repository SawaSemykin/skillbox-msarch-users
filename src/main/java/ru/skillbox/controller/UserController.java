package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.dto.UserDto;
import ru.skillbox.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public long createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping(path = "/{id}")
    public long updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping(path = "/{id}")
    public long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/{id}/subscriptions/{subscriptionId}")
    public void subscribe(@PathVariable(name = "id") long id, @PathVariable(name = "subscriptionId") long subscriptionId) {
        userService.subscribe(id, subscriptionId);

    }

    @DeleteMapping(path = "/{id}/subscriptions/{subscriptionId}")
    public void unsubscribe(@PathVariable(name = "id") long id, @PathVariable(name = "subscriptionId") long subscriptionId) {
        userService.unsubscribe(id, subscriptionId);

    }

    @GetMapping(path = "/{id}/subscriptions")
    public List<UserDto> getSubscriptions(@PathVariable(name = "id") Long id) {
        return userService.getSubscriptions(id);
    }

    @GetMapping(path = "/{id}/subscribers")
    public List<UserDto> getSubscribers(@PathVariable(name = "id") Long id) {
        return userService.getSubscribers(id);
    }
}

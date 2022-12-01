package ru.skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.exception.ResourceNotFoundException;
import ru.skillbox.model.User;
import ru.skillbox.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public long createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping(path = "/{id}")
    public long updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public long deleteUser(@RequestBody User user, @PathVariable Long id) {
        return userService.deleteUser(user, id);
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
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
}

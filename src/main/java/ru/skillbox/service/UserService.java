package ru.skillbox.service;

import ru.skillbox.model.User;

import java.util.List;

public interface UserService {
    long createUser(User user);

    long updateUser(User user, Long id);

    long deleteUser(User user, Long id);

    User getUser(Long id);

    List<User> getUsers();

    void subscribe(long id, long subscriptionId);

    void unsubscribe(long id, long subscriptionId);
}

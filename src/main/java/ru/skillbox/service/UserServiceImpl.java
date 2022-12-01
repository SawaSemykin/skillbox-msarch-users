package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.exception.ResourceNotFoundException;
import ru.skillbox.exception.UserIdNotConsistentException;
import ru.skillbox.exception.UserIdNotProvidedException;
import ru.skillbox.model.User;
import ru.skillbox.repository.UserRepository;
import ru.skillbox.transactionManager.TransactionManager;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TransactionManager transactionManager;

    @Override
    public long createUser(User user) {
        return transactionManager.doInTransaction(() -> {
            var savedUser = userRepository.save(user);
            log.info("saved user. ID = {}", savedUser.getId());
            return savedUser.getId();

        });
    }

    @Override
    public long updateUser(User user, Long id) {
        validateUserId(user, id);
        return transactionManager.doInTransaction(() -> {
            userRepository.save(user);
            log.info("updated user. ID = {}", id);
            return id;
        });
    }

    private void validateUserId(User user, Long id) {
        if (user.getId() == null) {
            throw new UserIdNotProvidedException();
        }
        if (!user.getId().equals(id)) {
            throw new UserIdNotConsistentException();
        }
    }

    @Override
    public long deleteUser(User user, Long id) {
        validateUserId(user, id);
        return transactionManager.doInTransaction(() -> {
            user.setDeleted(true);
            userRepository.save(user);
            log.info("deleted user. ID = {}", user.getId());
            return user.getId();
        });
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAllNotDeleted();
    }

    @Override
    public void subscribe(long id, long subscriptionId) {
        User user = getUser(id);
        User subscription = getUser(subscriptionId);
        transactionManager.doInTransaction(() -> {
            user.subscribe(subscription);
            log.info("user ID={} subscribed to user ID={}", id, subscriptionId);
            return user;
        });
    }

    @Override
    public void unsubscribe(long id, long subscriptionId) {
        User user = getUser(id);
        User subscription = getUser(subscriptionId);
        transactionManager.doInTransaction(() -> {
            user.unsubscribe(subscription);
            log.info("user ID={} unsubscribed from user ID={}", id, subscriptionId);
            return user;
        });
    }
}
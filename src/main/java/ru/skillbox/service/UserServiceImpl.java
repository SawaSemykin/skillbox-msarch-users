package ru.skillbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.UserDto;
import ru.skillbox.exception.ResourceNotFoundException;
import ru.skillbox.exception.UserIdNotConsistentException;
import ru.skillbox.mapper.UserListMapper;
import ru.skillbox.mapper.UserMapper;
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

    private final UserMapper userMapper;

    private final UserListMapper userListMapper;

    @Override
    public long createUser(UserDto userDto) {
        return transactionManager.doInTransaction(() -> {
            var user = userMapper.toEntity(userDto);
            var savedUser = userRepository.save(user);
            log.info("saved user. ID = {}", savedUser.getId());
            return savedUser.getId();

        });
    }

    @Override
    public long updateUser(UserDto userDto, Long id) {
        validateUserId(userDto, id);
        return transactionManager.doInTransaction(() -> {
            var user = userMapper.toEntity(userDto);
            userRepository.save(user);
            log.info("updated user. ID = {}", id);
            return id;
        });
    }

    private void validateUserId(UserDto user, Long id) {
        if (!user.getId().equals(id)) {
            throw new UserIdNotConsistentException();
        }
    }

    @Override
    public long deleteUser(Long id) {
        var user = getUserInternal(id);
        return transactionManager.doInTransaction(() -> {
            user.setDeleted(true);
            userRepository.save(user);
            log.info("deleted user. ID = {}", user.getId());
            return user.getId();
        });
    }

    @Override
    public UserDto getUser(Long id) {
        var user = getUserInternal(id);
        return userMapper.toDto(user);
    }

    private User getUserInternal(Long id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<UserDto> getUsers() {
        var users = userRepository.findAllNotDeleted();
        return userListMapper.toDto(users);
    }

    @Override
    public void subscribe(long id, long subscriptionId) {
        var user = getUserInternal(id);
        var subscription = getUserInternal(subscriptionId);
        transactionManager.doInTransaction(() -> {
            user.subscribe(subscription);
            log.info("user ID={} subscribed to user ID={}", id, subscriptionId);
            return user;
        });
    }

    @Override
    public void unsubscribe(long id, long subscriptionId) {
        var user = getUserInternal(id);
        var subscription = getUserInternal(subscriptionId);
        transactionManager.doInTransaction(() -> {
            user.unsubscribe(subscription);
            log.info("user ID={} unsubscribed from user ID={}", id, subscriptionId);
            return user;
        });
    }

    @Override
    public List<UserDto> getSubscriptions(Long id) {
        var user = getUserInternal(id);
        return userListMapper.toDto(user.getSubscriptions());
    }

    @Override
    public List<UserDto> getSubscribers(Long id) {
        var user = getUserInternal(id);
        return userListMapper.toDto(user.getSubscribers());
    }
}
package ru.sergalas.notification.entity.user.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.repository.UserRepository;
import ru.sergalas.notification.entity.user.service.UserService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    Optional<User> checkUserByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    User createUserFromChatId(Long chatIt) {
        User user = User
                .builder()
                .action(UserActionEnum.FREE)
                .firstName("test")
                .chatId(chatIt)
                .registerAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }
    @Override
    public User findByChatId(Long chatIt) {
       Optional<User> user = checkUserByChatId(chatIt);
       return user.orElseThrow(() -> new NoSuchElementException("user not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void editActionAndCurrentNotification(Long chatId, UserActionEnum action, String id) {
        User user = findByChatId(chatId);
        user.setAction(action);
        user.setCurrentNotification(UUID.fromString(id));
        save(user);
    }

    @Override
    public void editActionAndCurrentNotification(User user, UserActionEnum action) {
        user.setAction(action);
        save(user);
    }


    @Override
    public User getUserByChatIdOrCreateNewUser(Long chatId) {
        return checkUserByChatId(chatId).orElseGet(() -> createUserFromChatId(chatId));
    }
}

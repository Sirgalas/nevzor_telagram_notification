package ru.sergalas.notification.entity.user.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.repository.UserRepository;
import ru.sergalas.notification.entity.user.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

     Optional<User> checkUserByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }
    User createUserFromChatId(Long chatIt) {
        User user = User.builder().action(UserActionEnum.FREE).chatId(chatIt).build();
        return userRepository.save(user);
    }
    @Override
    public User findByChatId(Long chatIt) {
       Optional<User> user = checkUserByChatId(chatIt);
       return user.orElseThrow(() -> new NoSuchElementException("user not found"));
    }



    @Override
    public User getUserByChatIdOrCreateNewUser(Long chatId) {
        return checkUserByChatId(chatId).orElseGet(() -> createUserFromChatId(chatId));
    }
}

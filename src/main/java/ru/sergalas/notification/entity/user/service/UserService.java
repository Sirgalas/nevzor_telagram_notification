package ru.sergalas.notification.entity.user.service;

import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;

import java.util.Optional;

public interface UserService {
    User getUserByChatIdOrCreateNewUser(Long chatId);
    User findByChatId(Long chatIt);
    User save(User user);
    void editActionAndCurrentNotification(Long chatId, UserActionEnum action, String id);
}

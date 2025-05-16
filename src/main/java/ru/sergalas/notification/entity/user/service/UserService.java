package ru.sergalas.notification.entity.user.service;

import ru.sergalas.notification.entity.user.entity.User;

import java.util.Optional;

public interface UserService {
    User getUserByChatIdOrCreateNewUser(Long chatId);
    User findByChatId(Long chatIt);
}

package ru.sergalas.notification.service.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.repository.UserRepository;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.handler.Handler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler implements Handler {
    UserService userService;
    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        Message message = (Message) object;
        User user = userService.getUserByChatIdOrCreateNewUser(message.getChatId());
        switch (user.getAction()) {
            case FREE -> {
                return null;
            }
        }
        throw new UnsupportedOperationException("The class %s method is not implemented".formatted(MessageHandler.class));
    }
}

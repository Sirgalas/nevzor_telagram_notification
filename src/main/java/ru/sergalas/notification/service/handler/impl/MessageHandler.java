package ru.sergalas.notification.service.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.handler.Handler;
import ru.sergalas.notification.service.patterns.messageHandler.answer.MessageDispatcher;

import  static ru.sergalas.notification.entity.user.enums.UserActionEnum.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler implements Handler {
    UserService userService;
    MessageDispatcher messageDispatcher;
    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) throws TelegramApiException {
        Message message = (Message) object;
        User user = userService.getUserByChatIdOrCreateNewUser(message.getChatId());
        return messageDispatcher.dispatch(user.getAction(),message,bot);
    }
}

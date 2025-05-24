package ru.sergalas.notification.service.patterns.messageHandler.answer.handler.impl;

import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.service.patterns.messageHandler.answer.handler.MessageHandler;

@Service
public class FreeMessageHandler implements MessageHandler {
    @Override
    public Boolean canHandle(UserActionEnum action) {
        return action == UserActionEnum.FREE;
    }

    @Override
    public BotApiMethod<?> handle(Message message, Bot bot) throws TelegramApiException {
        return null;
    }
}

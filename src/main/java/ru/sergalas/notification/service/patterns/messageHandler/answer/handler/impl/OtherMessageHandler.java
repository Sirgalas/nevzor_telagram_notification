package ru.sergalas.notification.service.patterns.messageHandler.answer.handler.impl;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.service.managers.notification.NotificationManager;
import ru.sergalas.notification.service.patterns.messageHandler.answer.handler.MessageHandler;

@Service
@RequiredArgsConstructor
public class OtherMessageHandler implements MessageHandler {
    private final NotificationManager notificationManager;
    @Override
    public Boolean canHandle(UserActionEnum action) {
        return  action == UserActionEnum.SENDING_TIME ||
                action == UserActionEnum.SENDING_DESCRIPTION ||
                action == UserActionEnum.SENDING_TITLE;
    }

    @Override
    public BotApiMethod<?> handle(Message message, Bot bot) throws TelegramApiException {
        return notificationManager.answerMessage(message, bot);
    }
}

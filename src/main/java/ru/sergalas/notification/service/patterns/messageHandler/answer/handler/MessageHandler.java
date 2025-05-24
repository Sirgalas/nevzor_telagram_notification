package ru.sergalas.notification.service.patterns.messageHandler.answer.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;

public interface MessageHandler {

    Boolean canHandle(UserActionEnum action);

    public BotApiMethod<?> handle(Message message, Bot bot) throws TelegramApiException;
}

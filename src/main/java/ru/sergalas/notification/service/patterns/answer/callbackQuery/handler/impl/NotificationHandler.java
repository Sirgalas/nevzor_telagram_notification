package ru.sergalas.notification.service.patterns.answer.callbackQuery.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.managers.notification.NotificationManager;
import ru.sergalas.notification.service.patterns.answer.callbackQuery.handler.CallbackHandler;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationHandler implements CallbackHandler {
    NotificationManager notificationManager;
    @Override
    public Boolean canHandle(String callbackType) {
        return "NOTIFICATION".equalsIgnoreCase(callbackType);
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException {
        return notificationManager.answerQuery(query,words,bot);
    }
}

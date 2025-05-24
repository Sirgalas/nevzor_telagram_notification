package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface FourHandler {
    BotApiMethod<?> handle(CallbackQuery query, String id) throws TelegramApiException;
}

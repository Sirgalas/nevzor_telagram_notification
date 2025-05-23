package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ThreeHandler {
    BotApiMethod<?> handle(CallbackQuery query, String id) throws TelegramApiException;
}

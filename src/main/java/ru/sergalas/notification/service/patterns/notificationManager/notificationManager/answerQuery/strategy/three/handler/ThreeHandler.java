package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.three.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;

public interface ThreeHandler {
    BotApiMethod<?>  handle(CallbackQuery query, String id, Bot bot) throws TelegramApiException;
}

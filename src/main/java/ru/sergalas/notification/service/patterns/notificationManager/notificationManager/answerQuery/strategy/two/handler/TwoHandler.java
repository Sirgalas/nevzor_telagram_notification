package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;

public interface TwoHandler {
    BotApiMethod<?> handle(CallbackQuery query, Bot bot);
}

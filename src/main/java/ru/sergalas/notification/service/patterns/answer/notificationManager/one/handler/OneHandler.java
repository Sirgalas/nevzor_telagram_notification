package ru.sergalas.notification.service.patterns.answer.notificationManager.one.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;

public interface OneHandler {
    BotApiMethod<?> handle(CallbackQuery query,  Bot bot);
}

package ru.sergalas.notification.service.patterns.updateDispatcher.callbackQuery.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;

public interface CallbackHandler {
    Boolean canHandle(String callbackType);
    BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException;
}

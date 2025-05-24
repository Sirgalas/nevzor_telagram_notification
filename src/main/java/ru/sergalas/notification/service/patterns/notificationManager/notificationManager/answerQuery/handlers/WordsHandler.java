package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;

public interface WordsHandler {
    Boolean canHandle(Integer word);
    BotApiMethod<?> handle(CallbackQuery query,String[] words, Bot bot) throws TelegramApiException;
}

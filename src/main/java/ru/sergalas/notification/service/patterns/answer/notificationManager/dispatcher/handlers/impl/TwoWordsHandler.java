package ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.WordsHandler;

public class TwoWordsHandler implements WordsHandler {
    @Override
    public Boolean canHandle(Integer word) {
        return word == 2;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) {
        return process(query,words[1]);
    }

    private BotApiMethod<?> process(CallbackQuery query, String id) {
        return null;
    }
}

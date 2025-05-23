package ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.WordsHandler;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three.ThreeStrategy;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three.handler.ThreeHandler;

public class ThreeWordsHandler implements WordsHandler {
    ThreeStrategy threeStrategy;

    @Override
    public Boolean canHandle(Integer word) {
        return word == 3;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException {
        return process(query, words, bot);
    }

    private BotApiMethod<?> process(CallbackQuery query, String[] words, Bot bot ) throws TelegramApiException {
        String callBackType = words[1];
        ThreeHandler threeHandler =  threeStrategy.getStrategy(callBackType);
        if(threeHandler == null) {
            throw new UnsupportedOperationException("Unknown callback type: %s".formatted(callBackType));
        }
        return threeHandler.handle(query, words[3]);
    }
}

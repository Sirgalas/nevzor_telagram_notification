package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers.WordsHandler;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four.FourStrategy;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four.handler.FourHandler;

public class FourWordsHandler implements WordsHandler {
    FourStrategy threeStrategy;

    @Override
    public Boolean canHandle(Integer word) {
        return word == 4;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException {
        return process(query, words, bot);
    }

    private BotApiMethod<?> process(CallbackQuery query, String[] words, Bot bot ) throws TelegramApiException {
        String callBackType = words[1];
        FourHandler threeHandler =  threeStrategy.getStrategy(callBackType);
        if(threeHandler == null) {
            throw new UnsupportedOperationException("Unknown callback type: %s".formatted(callBackType));
        }
        return threeHandler.handle(query, words[3]);
    }
}

package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers.WordsHandler;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.TwoStrategy;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler.TwoHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TwoWordsHandler implements WordsHandler {
    TwoStrategy oneStrategy;
    @Override
    public Boolean canHandle(Integer word) {
        return word == 2;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) {

        String callbackType = words[1];
        TwoHandler strategy = oneStrategy.getStrategy(callbackType);

        if (strategy == null) {
            throw new UnsupportedOperationException("Unknown callback type: " + callbackType);
        }

        return strategy.handle(query, bot);
    }
}

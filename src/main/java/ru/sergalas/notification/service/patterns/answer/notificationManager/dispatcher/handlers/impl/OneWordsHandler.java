package ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.WordsHandler;
import ru.sergalas.notification.service.patterns.answer.notificationManager.one.OneStrategy;
import ru.sergalas.notification.service.patterns.answer.notificationManager.one.handler.OneHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OneWordsHandler implements WordsHandler {
    OneStrategy oneStrategy;
    @Override
    public Boolean canHandle(Integer word) {
        return word == 1;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) {

        String callbackType = words[0];
        OneHandler strategy = oneStrategy.getStrategy(callbackType);

        if (strategy == null) {
            throw new UnsupportedOperationException("Unknown callback type: " + callbackType);
        }

        return strategy.handle(query, bot);
    }
}

package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.handlers.WordsHandler;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.three.ThreeStrategy;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.three.handler.ThreeHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThreeWordsHandler implements WordsHandler {
    ThreeStrategy twoStrategy;

    @Override
    public Boolean canHandle(Integer word) {
        return word == 3;
    }
    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException {
        return process(query,words, bot);
    }

    private BotApiMethod<?> process(CallbackQuery query, String[] words, Bot bot ) throws TelegramApiException {
        String callbackType = words[1];
        ThreeHandler strategy = twoStrategy.getStrategy(callbackType);

        if (strategy == null) {
            throw new UnsupportedOperationException("Unknown callback type: %s".formatted(callbackType));
        }

        return strategy.handle(query, words[2], bot);
    }
}

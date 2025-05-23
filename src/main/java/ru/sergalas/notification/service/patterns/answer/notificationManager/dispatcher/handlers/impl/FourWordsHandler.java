package ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.WordsHandler;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.TwoStrategy;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.handler.TwoHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FourWordsHandler implements WordsHandler {
    TwoStrategy twoStrategy;

    @Override
    public Boolean canHandle(Integer word) {
        return word == 4;
    }
    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException {
        return process(query,words, bot);
    }

    private BotApiMethod<?> process(CallbackQuery query, String[] words, Bot bot ) throws TelegramApiException {
        String callbackType = words[1];
        TwoHandler strategy = twoStrategy.getStrategy(callbackType);

        if (strategy == null) {
            throw new UnsupportedOperationException("Unknown callback type: %s".formatted(callbackType));
        }

        return strategy.handle(query, words[2], bot);
    }
}

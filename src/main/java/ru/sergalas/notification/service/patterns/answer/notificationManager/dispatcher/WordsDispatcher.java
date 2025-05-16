package ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.handlers.WordsHandler;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WordsDispatcher {
    private final List<WordsHandler> handlers;

    public BotApiMethod<?> dispatch(CallbackQuery query, String[] words, Bot bot) {
        return handlers.stream()
                .filter(h -> h.canHandle(words.length))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("нет обработчика для длины ".formatted(words.length)))
                .handle(query, words, bot);
    }
}

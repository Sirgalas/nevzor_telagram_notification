package ru.sergalas.notification.service.patterns.updateDispatcher.callbackQuery.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.managers.MainManager;
import ru.sergalas.notification.service.patterns.updateDispatcher.callbackQuery.handler.CallbackHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainHandler implements CallbackHandler {

    MainManager mainManager;

    @Override
    public Boolean canHandle(String callbackType) {
        return "MAIN".equalsIgnoreCase(callbackType);
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String[] words, Bot bot) {
        return mainManager.answerQuery(query,words,bot);
    }
}

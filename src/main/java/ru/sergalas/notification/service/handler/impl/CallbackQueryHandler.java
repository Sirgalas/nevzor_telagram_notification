package ru.sergalas.notification.service.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.handler.Handler;
import ru.sergalas.notification.service.patterns.answer.callbackQuery.CallbackHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler implements Handler {

    CallbackHandler factory;

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        CallbackQuery query = (CallbackQuery) object;
        String[] words = query.getData().split("_");
        String callback = words[0];

        ru.sergalas.notification.service.patterns.answer.callbackQuery.handler.CallbackHandler handler = factory.getHandler(callback);
        return handler.handle(query,words,bot);
    }
}

package ru.sergalas.notification.service.patterns.answer.notificationManager.two.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.service.patterns.answer.notificationManager.two.handler.TwoHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BackTwoHandler implements TwoHandler {
    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id) {
        return null;
    }
}

package ru.sergalas.notification.service.patterns.answer.notificationManager.two.handler.impl;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.service.patterns.answer.notificationManager.two.handler.TwoHandler;

public class DoneTwoHandler implements TwoHandler {
    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id) {
        return null;
    }
}

package ru.sergalas.notification.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UpdateDispatcher {
    public BotApiMethod<?> dispatch(Update update) {
        return null;
    }
}

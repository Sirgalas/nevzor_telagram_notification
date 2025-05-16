package ru.sergalas.notification.service.handler;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.sergalas.notification.bot.Bot;

public interface Handler {
    public BotApiMethod<?> answer (BotApiObject object, Bot bot);
}

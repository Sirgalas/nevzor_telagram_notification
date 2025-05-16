package ru.sergalas.notification.service.listener;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergalas.notification.bot.Bot;

public interface CommandListener {
    BotApiMethod<?> answerCommand(Message message, Bot bot);
}

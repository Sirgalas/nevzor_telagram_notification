package ru.sergalas.notification.service.listener;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;

public interface QueryListener {
    BotApiMethod<?> answerQuery(CallbackQuery query,String[] words, Bot bot);
}

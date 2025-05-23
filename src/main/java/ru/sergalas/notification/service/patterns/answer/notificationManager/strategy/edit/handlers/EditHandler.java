package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.edit.handlers;


import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;

public interface EditHandler {
    Boolean canHandle(UserActionEnum action);
    BotApiMethod<?> process(Message message, User user, Bot bot) throws TelegramApiException;
}

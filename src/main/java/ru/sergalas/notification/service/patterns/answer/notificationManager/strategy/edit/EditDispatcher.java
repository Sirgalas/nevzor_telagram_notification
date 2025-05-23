package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.edit;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.edit.handlers.EditHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditDispatcher {
    private final List<EditHandler> handlers;

    public BotApiMethod<?> dispatch(UserActionEnum action, Message message, User user, Bot bot) throws TelegramApiException {
        return handlers.stream()
            .filter(handler -> handler.canHandle(action))
            .findFirst()
            .orElseThrow(() -> new UnsupportedOperationException("Нет обработчика для  " + action.name()))
            .process(message,user,bot);
    }

}

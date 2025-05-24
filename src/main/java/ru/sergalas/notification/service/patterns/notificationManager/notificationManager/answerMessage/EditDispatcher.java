package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerMessage.handlers.EditHandler;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditDispatcher {
    private final List<EditHandler> handlers;

    public BotApiMethod<?> dispatch(UserActionEnum action, Message message, User user, Bot bot) throws TelegramApiException {


        Optional<EditHandler> handler = handlers.stream()
            .filter(h -> h.canHandle(action))
            .findFirst();
            return handler.isPresent() ?
                handler.get().process(message,user,bot)
                : null;
    }

}

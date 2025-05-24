package ru.sergalas.notification.service.patterns.messageHandler.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.service.patterns.messageHandler.answer.handler.MessageHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDispatcher {
    List<MessageHandler> handlers;

    public BotApiMethod<?> dispatch(UserActionEnum action, Message message, Bot bot) throws TelegramApiException {
        return handlers.stream()
                .filter(handler -> handler.canHandle(action))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("No handler for action: " + action))
                .handle(message, bot);
    }
}

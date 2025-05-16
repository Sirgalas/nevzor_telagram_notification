package ru.sergalas.notification.service.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.handler.Handler;
import ru.sergalas.notification.service.managers.MainManager;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler implements Handler {
    MainManager mainManager;

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        Message message = (Message) object;
        if("/start".equals(message.getText())) {
            return mainManager.answerCommand(message,bot);
        }
        throw new UnsupportedOperationException("The class %s method is not implemented".formatted(CommandHandler.class));

    }
}

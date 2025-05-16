package ru.sergalas.notification.service.managers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.listener.CommandListener;
import ru.sergalas.notification.service.listener.QueryListener;
import ru.sergalas.notification.service.managers.contract.AbstractManagers;

@Service
public class MainManager extends AbstractManagers implements CommandListener, QueryListener {
    @Override
    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return sendHello(message.getChatId());
    }

    @Override
    public BotApiMethod<?> answerQuery(CallbackQuery query, String[] words, Bot bot) {
        return null;
    }

    @Override
    public BotApiMethod<?> mainMenu(Message message, Bot bot) {
        return null;
    }

    @Override
    public BotApiMethod<?> mainMenu(CallbackQuery query, Bot bot) {
        return null;
    }

    private BotApiMethod<?> sendHello(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("Hello !")
                .build();
    }
}

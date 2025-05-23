package ru.sergalas.notification.service.managers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.factory.KeyboardFactory;
import ru.sergalas.notification.service.listener.CommandListener;
import ru.sergalas.notification.service.listener.QueryListener;
import ru.sergalas.notification.service.managers.contract.AbstractManagers;

import java.util.List;

import static ru.sergalas.notification.enums.CallbackEnum.NOTIFICATION_MAIN;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager extends AbstractManagers implements CommandListener, QueryListener {

    KeyboardFactory factory;


    @Override
    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Привет !")
                .replyMarkup(factory.createInlineKeyboardMarkup(
                        List.of("Напоминалки"),
                        List.of(1),
                        List.of(NOTIFICATION_MAIN.name())
                ))
                .build();
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
        return EditMessageText.builder()
            .chatId(query.getMessage().getChatId())
            .messageId(query.getMessage().getMessageId())
            .text("Привет !")
            .replyMarkup(factory.createInlineKeyboardMarkup(
                    List.of("Напоминалки"),
                    List.of(1),
                    List.of(NOTIFICATION_MAIN.name())
            ))
            .build();
    }

    private BotApiMethod<?> sendHello(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("Hello !")
                .build();
    }
}

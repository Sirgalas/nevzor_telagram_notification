package ru.sergalas.notification.service.managers.notification;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.factory.KeyboardFactory;
import ru.sergalas.notification.service.helpers.ReplayMarkupHelper;
import ru.sergalas.notification.service.patterns.answer.notificationManager.dispatcher.WordsDispatcher;

import java.util.List;

import static ru.sergalas.notification.enums.CallbackEnum.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationManager {

    KeyboardFactory keyboardFactory;
    WordsDispatcher dispatcher;
    ReplayMarkupHelper replayMarkupHelper;

    public BotApiMethod<?> mainMenu(Message message, Bot bot) {
        return null;
    }

    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return null;
    }

    public BotApiMethod<?> answerMessage(Message message, Bot bot) {
        return null;
    }

    public BotApiMethod<?> answerQuery(CallbackQuery query, String[] words, Bot bot) throws TelegramApiException {
        return  dispatcher.dispatch(query,words,bot);
    }


    private BotApiMethod<?> greetings(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("Hello !")
                .replyMarkup(
                        keyboardFactory.createInlineKeyboardMarkup(
                                List.of("Напоминалки"),
                                List.of(1),
                                List.of(NOTIFICATION_MAIN.name()))
                )
                .build();
    }


}

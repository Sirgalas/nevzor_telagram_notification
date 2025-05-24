package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.factory.KeyboardFactory;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler.TwoHandler;

import java.util.List;

import static ru.sergalas.notification.enums.CallbackEnum.NOTIFICATION_NEW;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainOneHandler implements TwoHandler {
    KeyboardFactory keyboardFactory;
    @Override
    public BotApiMethod<?> handle(CallbackQuery query,  Bot bot) {
        return SendMessage.builder()
            .chatId(query.getMessage().getChatId())
            .text("***")
            .replyMarkup(keyboardFactory.createInlineKeyboardMarkup(
                    List.of("Добавить уведомление"),
                    List.of(1),
                    List.of(NOTIFICATION_NEW.name())
            ))
            .build();
    }
}

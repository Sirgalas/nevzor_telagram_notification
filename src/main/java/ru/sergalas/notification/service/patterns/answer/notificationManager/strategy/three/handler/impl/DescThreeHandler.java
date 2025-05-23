package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.factory.KeyboardFactory;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three.handler.ThreeHandler;

import java.util.List;

import static ru.sergalas.notification.enums.CallbackEnum.NOTIFICATION_BACK_;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescThreeHandler implements ThreeHandler {
    UserService userService;
    private final KeyboardFactory keyboardFactory;

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id) throws TelegramApiException {
        userService.editActionAndCurrentNotification(query.getMessage().getChatId(),UserActionEnum.SENDING_DESCRIPTION,id);
        return EditMessageText.builder()
            .text("⚡\uFE0F Добавьте или измените описание, просто напишите в чат тест, который бы хотели получить")
            .messageId(query.getMessage().getMessageId())
            .chatId(query.getMessage().getChatId())
            .replyMarkup(
                keyboardFactory.createInlineKeyboardMarkup(
                    List.of("\uD83D\uDD19 Назад"),
                    List.of(1),
                    List.of(NOTIFICATION_BACK_ + id)
                )
            )
            .build();
    }
}

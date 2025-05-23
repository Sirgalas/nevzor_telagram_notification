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

import static ru.sergalas.notification.enums.CallbackEnum.NOTIFICATION_BACK_;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TitleThreeHandler implements ThreeHandler {
    UserService userService;
    KeyboardFactory keyboardFactory;

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id) throws TelegramApiException {
        userService.editActionAndCurrentNotification(query.getMessage().getChatId(), UserActionEnum.SENDING_TITLE, id);
        return EditMessageText.builder()
                .text("⚡\uFE0F Опишите краткий заголовок в следующем сообщение, чтобы вам было сразу понятно, что я вам напоминаю")
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

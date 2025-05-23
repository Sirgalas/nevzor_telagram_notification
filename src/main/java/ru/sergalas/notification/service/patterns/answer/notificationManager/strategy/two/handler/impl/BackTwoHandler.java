package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.service.helpers.ReplayMarkupHelper;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.handler.TwoHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BackTwoHandler implements TwoHandler {
    ReplayMarkupHelper replayMarkupHelper;
    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id, Bot bot) {
        return EditMessageText.builder()
            .chatId(query.getMessage().getChatId())
            .messageId(query.getMessage().getMessageId())
            .text("Настройте уведомление")
            .replyMarkup(replayMarkupHelper.editNotificationReplyMarkup(id))
            .build();
    }
}

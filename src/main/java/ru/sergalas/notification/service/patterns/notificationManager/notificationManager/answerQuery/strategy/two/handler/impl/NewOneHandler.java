package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.notification.repository.NotificationRepository;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.enums.NotificationStatus;
import ru.sergalas.notification.service.helpers.ReplayMarkupHelper;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler.TwoHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewOneHandler implements TwoHandler {
    NotificationRepository notificationRepository;
    UserService userService;
    ReplayMarkupHelper replayMarkupHelper;

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, Bot bot) {
        String id = notificationRepository.save(
            Notification
                .builder()
                .user(
                    userService.
                        findByChatId(query.getMessage().getChatId())
                )
                .status(NotificationStatus.BUILDING)
                .build()
        ).getId()
        .toString();
        return EditMessageText.builder()
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .text("Настройте уведомление")
                .replyMarkup(replayMarkupHelper.editNotificationReplyMarkup(id))
                .build();
    }

}

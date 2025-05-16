package ru.sergalas.notification.service.patterns.answer.notificationManager.one.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.notification.repository.NotificationRepository;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.enums.NotificationStatus;
import ru.sergalas.notification.service.patterns.answer.notificationManager.one.handler.OneHandler;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewOneHandler implements OneHandler {
    NotificationRepository notificationRepository;
    UserService userService;
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
        ).getId().toString();
        return EditMessageText.builder()
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .text("Настройте уведомление")
                .replyMarkup(editNotificationReplayMarkup(id))
                .build();
    }

    private InlineKeyboardMarkup editNotificationReplayMarkup(String id) {
        List<String> text = new ArrayList<>();
        List<Integer> config = new ArrayList<>();
        List <String> data = new ArrayList<>();

        return null;
    }
}

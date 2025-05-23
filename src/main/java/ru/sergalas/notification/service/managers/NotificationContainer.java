package ru.sergalas.notification.service.managers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import ru.sergalas.notification.enums.NotificationStatus;

@Slf4j
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class NotificationContainer implements Runnable{

    Bot bot;
    Long chatId;
    Notification notification;
    NotificationService notificationService;

    public NotificationContainer(
        Bot bot,
        Long chatId,
        Notification notification,
        NotificationService notificationService
    ) {
        this.bot = bot;
        this.chatId = chatId;
        this.notification = notification;
        this.notificationService = notificationService;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(notification.getSeconds() * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        try {
            bot.execute(
                    sendNotification()
            );
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
        notificationService.changeStatus(notification,NotificationStatus.FINISHED);
    }

    private BotApiMethod<?> sendNotification() {
        return SendMessage.builder()
            .chatId(chatId)
            .text(
                "⚡\uFE0F Напоминание: %s \n ❗\uFE0F %s \n\n".formatted(notification.getTitle(),notification.getDescription())
            )
            .build();
    }
}

package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import ru.sergalas.notification.enums.NotificationStatus;
import ru.sergalas.notification.service.factory.KeyboardFactory;
import ru.sergalas.notification.service.managers.NotificationContainer;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.handler.TwoHandler;

import java.util.List;

import static ru.sergalas.notification.enums.CallbackEnum.MAIN;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoneTwoHandler implements TwoHandler {

    NotificationService notificationService;
    KeyboardFactory keyboardFactory;

    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id,Bot bot) throws TelegramApiException {

        var notification = notificationService.findById(id);
        if (notification.getTitle() == null  || notification.getTitle().isBlank() || notification.getSeconds() == null) {
            return AnswerCallbackQuery.builder()
                .callbackQueryId(query.getId())
                .text("Заполните обязательные значения: Заголовок и Время")
                .build();
        }
        bot.execute(
            AnswerCallbackQuery.builder()
                .text("Уведомление придет к вам через %s секунд \uD83D\uDC40".formatted(notification.getSeconds()))
                .callbackQueryId(query.getId())
                .build()
        );
        notificationService.changeStatus(notification, NotificationStatus.WAITING);

        Thread.startVirtualThread(
            new NotificationContainer(
                bot,
                query.getMessage().getChatId(),
                notification,
                notificationService
            )
        );
        return EditMessageText.builder()
            .text("✅ Успешно")
            .chatId(query.getMessage().getChatId())
            .messageId(query.getMessage().getMessageId())
            .replyMarkup(
                keyboardFactory.createInlineKeyboardMarkup(
                    List.of("На главную"),
                    List.of(1),
                    List.of(MAIN.name())
                )
            )
            .build();
    }
}

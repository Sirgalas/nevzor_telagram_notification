package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerMessage.handlers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.factory.KeyboardFactory;
import ru.sergalas.notification.service.helpers.ReplayMarkupHelper;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerMessage.handlers.EditHandler;

import java.util.List;
import java.util.regex.Pattern;
import static ru.sergalas.notification.enums.CallbackEnum.NOTIFICATION_BACK_;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeHandler implements EditHandler {
    NotificationService notificationService;
    KeyboardFactory keyboardFactory;
    UserService userService;
    ReplayMarkupHelper helper;

    @Override
    public Boolean canHandle(UserActionEnum action) {
        return action == UserActionEnum.SENDING_TIME;
    }

    @Override
    public BotApiMethod<?> process(Message message, User user, Bot bot) throws TelegramApiException {
        String messageText = message.getText().strip();
        if (!isValidTimeFormat(messageText)) {
            return buildErrorResponse(message, user);
        }
        Notification notification = notificationService.findById(user.getCurrentNotification().toString());
        notification.setSeconds(parseTimeToSeconds(messageText));
        notificationService.save(notification);
        userService.editActionAndCurrentNotification(user,UserActionEnum.FREE);
        return helper.mainMenu(message, bot);
    }

    private Boolean isValidTimeFormat(String input) {
        return Pattern.matches("^[0-9]{2}:[0-9]{2}:[0-9]{2}$", input);
    }

    private Long parseTimeToSeconds(String time) {
        String[] nums = time.split(":");
        return Long.parseLong(nums[0]) * 3600
                + Long.parseLong(nums[1]) * 60
                + Long.parseLong(nums[2]);
    }

    private BotApiMethod<?> buildErrorResponse(Message message, User user) {
        return SendMessage.builder()
            .text("Некорректный формат времени...")
            .chatId(message.getChatId())
            .replyMarkup(
                keyboardFactory.createInlineKeyboardMarkup(
                    List.of("\uD83D\uDD19 Назад"),
                    List.of(1),
                    List.of(NOTIFICATION_BACK_ + String.valueOf(user.getCurrentNotification()))
                )
            )
            .build();
    }
}

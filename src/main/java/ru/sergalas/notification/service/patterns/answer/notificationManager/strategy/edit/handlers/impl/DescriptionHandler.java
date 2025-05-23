package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.edit.handlers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.helpers.ReplayMarkupHelper;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.edit.handlers.EditHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionHandler implements EditHandler {
    NotificationService notificationService;
    UserService userService;
    ReplayMarkupHelper replayMarkupHelper;

    @Override
    public Boolean canHandle(UserActionEnum action) {
        return action == UserActionEnum.SENDING_DESCRIPTION;
    }

    @Override
    public BotApiMethod<?> process(Message message, User user, Bot bot) throws TelegramApiException {
        Notification notification = notificationService.findById(user.getCurrentNotification().toString());
        notification.setDescription(message.getText());
        notificationService.save(notification);
        userService.editActionAndCurrentNotification(user, UserActionEnum.FREE);
        return replayMarkupHelper.mainMenu(message, bot);
    }
}

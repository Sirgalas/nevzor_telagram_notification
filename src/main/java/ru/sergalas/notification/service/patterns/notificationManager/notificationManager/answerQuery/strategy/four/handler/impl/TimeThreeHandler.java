package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.entity.user.enums.UserActionEnum;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.helpers.ReplayMarkupHelper;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four.handler.FourHandler;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeThreeHandler implements FourHandler {
    UserService userService;
    ReplayMarkupHelper replayMarkupHelper;
    @Override
    public BotApiMethod<?> handle(CallbackQuery query, String id) throws TelegramApiException {
        userService.editActionAndCurrentNotification(query.getMessage().getChatId(), UserActionEnum.SENDING_TIME,id);
        return replayMarkupHelper.backButton(
            "⚡\uFE0F Введите время, по прошествии которого хотите получить напоминание\nФормат - ЧЧ:ММ:СС\nНапример - (01:30:00) - полтора часа",
            query.getMessage().getMessageId(),
            query.getMessage().getChatId(),
            id
        );
    }
}

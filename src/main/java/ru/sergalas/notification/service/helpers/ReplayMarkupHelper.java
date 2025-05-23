package ru.sergalas.notification.service.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import static ru.sergalas.notification.enums.CallbackEnum.*;

import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.factory.KeyboardFactory;

import java.util.ArrayList;
import java.util.List;
import static ru.sergalas.notification.enums.CallbackEnum.*;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReplayMarkupHelper {
    NotificationService notificationService;
    KeyboardFactory keyboardFactory;
    UserService userService;

    public InlineKeyboardMarkup editNotificationReplyMarkup(String id) {
        List<String> text = new ArrayList<>();
        var notification = notificationService.findById(id);
        text.add(getButtonText((notification.getTitle() != null && !notification.getTitle().isBlank()),"Заголовок"));
        text.add(getButtonText((notification.getSeconds() != null && notification.getSeconds() != 0),"Время"));
        text.add(getButtonText((notification.getDescription() != null && !notification.getDescription().isBlank()),"Описание"));

        text.add("\uD83D\uDD19 Главная");
        text.add("\uD83D\uDD50 Готово");
        return keyboardFactory.createInlineKeyboardMarkup(
            text,
            List.of(2, 1, 2),
            List.of(
                NOTIFICATION_EDIT_TITLE_.name() + id,
                NOTIFICATION_EDIT_TIME_.name() + id,
                NOTIFICATION_EDIT_DESC_.name() + id,
                MAIN.name(),
                NOTIFICATION_DONE_.name() + id
            )
        );
    }

    private String getButtonText(Boolean condition, String text) {
        if(condition) {
            return "✅ %s".formatted(text);
        }
        return "❌ %s".formatted(text);
    }

    public BotApiMethod<?> mainMenu(Message message, Bot bot) {
        return SendMessage.builder()
            .chatId(message.getChatId())
            .text("Настройте уведомление")
            .replyMarkup(
                editNotificationReplyMarkup(
                    String.valueOf(
                        userService.findByChatId(message.getChatId())
                            .getCurrentNotification()
                    )
                )
            )
            .build();
    }

    public BotApiMethod<?> mainMenu(CallbackQuery query, Bot bot) {
        return EditMessageText.builder()
            .chatId(query.getMessage().getChatId())
            .messageId(query.getMessage().getMessageId())
            .text("###")
            .replyMarkup(
                keyboardFactory.createInlineKeyboardMarkup(
                    List.of("Добавить уведомление"),
                    List.of(1),
                    List.of(NOTIFICATION_NEW.name())
                )
            )
            .build();
    }
}

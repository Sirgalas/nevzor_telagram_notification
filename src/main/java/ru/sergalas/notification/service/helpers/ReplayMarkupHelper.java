package ru.sergalas.notification.service.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import static ru.sergalas.notification.enums.CallbackEnum.*;
import ru.sergalas.notification.service.factory.KeyboardFactory;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReplayMarkupHelper {
    NotificationService notificationService;
    KeyboardFactory keyboardFactory;

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
}

package ru.sergalas.notification.bot;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.config.TelegramProperties;
import ru.sergalas.notification.service.UpdateDispatcher;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Bot extends TelegramWebhookBot {
    TelegramProperties properties;
    UpdateDispatcher dispatcher;

    public Bot(TelegramProperties properties, UpdateDispatcher dispatcher) {
        super(properties.getToken());
        this.properties = properties;
        this.dispatcher = dispatcher;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return dispatcher.dispatch(update, this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotPath() {
        return properties.getUrl();
    }

    @Override
    public String getBotUsername() {
        return properties.getName();
    }
}

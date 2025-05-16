package ru.sergalas.notification.bot;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
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
        return dispatcher.dispatch(update, this);
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

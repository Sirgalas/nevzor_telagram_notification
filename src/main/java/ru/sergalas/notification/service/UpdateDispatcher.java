package ru.sergalas.notification.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergalas.notification.bot.Bot;
import ru.sergalas.notification.entity.user.entity.User;
import ru.sergalas.notification.entity.user.service.UserService;
import ru.sergalas.notification.service.handler.impl.CallbackQueryHandler;
import ru.sergalas.notification.service.handler.impl.CommandHandler;
import ru.sergalas.notification.service.handler.impl.MessageHandler;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {
    MessageHandler messageHandler;
    CommandHandler commandHandler;
    CallbackQueryHandler callbackQueryHandler;
    UserService userService;

    public BotApiMethod<?> dispatch(Update update, Bot bot) throws TelegramApiException {
        if(update.hasCallbackQuery()) {
            userService.getUserByChatIdOrCreateNewUser(update.getCallbackQuery().getMessage().getChatId());
            return callbackQueryHandler.answer(update.getCallbackQuery(), bot);
        }
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(message.hasText()) {
                return getBotApiMethodHandled(message,bot);
            }
        }
        log.error("Unsupported update type: {}", update);
        return null;
    }


    public BotApiMethod<?> getBotApiMethodHandled(Message message, Bot bot) throws TelegramApiException {
        if(message.getText().startsWith("/")) {
            return commandHandler.answer(message, bot);
        }
        return messageHandler.answer(message, bot);
    }

}

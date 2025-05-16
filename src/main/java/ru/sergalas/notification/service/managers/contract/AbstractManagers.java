package ru.sergalas.notification.service.managers.contract;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergalas.notification.bot.Bot;

public abstract class AbstractManagers {

    public abstract BotApiMethod<?> mainMenu(Message message, Bot bot);

    public abstract BotApiMethod<?> mainMenu(CallbackQuery query, Bot bot);
}

package ru.sergalas.notification.service.patterns.updateDispatcher.callbackQuery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CallbackHandler {

    private final List<ru.sergalas.notification.service.patterns.updateDispatcher.callbackQuery.handler.CallbackHandler> handlers;

    public ru.sergalas.notification.service.patterns.updateDispatcher.callbackQuery.handler.CallbackHandler getHandler(String callbackType) {
        return handlers.stream()
                .filter(h -> h.canHandle(callbackType))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException(
                        "No handler found for: " + callbackType));
    }
}

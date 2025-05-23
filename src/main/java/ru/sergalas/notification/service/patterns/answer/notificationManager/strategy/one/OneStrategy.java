package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.one.handler.OneHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OneStrategy {

    private Map<String, OneHandler> strategies;


    public OneStrategy(@Autowired List<OneHandler> strategiesList) {
        strategies = strategiesList
            .stream()
            .collect(
                Collectors.toMap(
                    strategy -> strategy.getClass().getSimpleName().replace("OneHandler","").toUpperCase(),
                    Function.identity()
        ));
    }

    public OneHandler getStrategy(String callbackName) {
        return strategies.get(callbackName);
    }
}

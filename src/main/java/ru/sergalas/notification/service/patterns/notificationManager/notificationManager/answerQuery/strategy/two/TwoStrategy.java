package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.two.handler.TwoHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TwoStrategy {

    private Map<String, TwoHandler> strategies;


    public TwoStrategy(@Autowired List<TwoHandler> strategiesList) {
        strategies = strategiesList
            .stream()
            .collect(
                Collectors.toMap(
                    strategy -> strategy.getClass().getSimpleName().replace("OneHandler","").toUpperCase(),
                    Function.identity()
        ));
    }

    public TwoHandler getStrategy(String callbackName) {
        return strategies.get(callbackName);
    }
}

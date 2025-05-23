package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.two.handler.TwoHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TwoStrategy {
    Map<String, TwoHandler> twoStrategies;

    public TwoStrategy(@Autowired List<TwoHandler> strategiesList) {
        twoStrategies = strategiesList
            .stream()
            .collect(
                Collectors.toMap(
                    strategy -> strategy.getClass().getSimpleName().replace("TwoHandler","").toUpperCase(),
                    Function.identity()
                )
            );
    }

    public TwoHandler getStrategy(String callbackName) {
        return twoStrategies.get(callbackName);
    }
}

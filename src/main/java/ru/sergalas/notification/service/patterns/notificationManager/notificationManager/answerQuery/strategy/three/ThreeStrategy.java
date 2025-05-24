package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.three;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.three.handler.ThreeHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ThreeStrategy {
    Map<String, ThreeHandler> twoStrategies;

    public ThreeStrategy(@Autowired List<ThreeHandler> strategiesList) {
        twoStrategies = strategiesList
            .stream()
            .collect(
                Collectors.toMap(
                    strategy -> strategy.getClass().getSimpleName().replace("TwoHandler","").toUpperCase(),
                    Function.identity()
                )
            );
    }

    public ThreeHandler getStrategy(String callbackName) {
        return twoStrategies.get(callbackName);
    }
}

package ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sergalas.notification.service.patterns.answer.notificationManager.strategy.three.handler.ThreeHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreeStrategy {
    Map<String, ThreeHandler> threeStrategies;

    public ThreeStrategy(@Autowired List<ThreeHandler> strategyList) {
        threeStrategies = strategyList.stream().collect(
            Collectors.toMap(
                strategy -> strategy.getClass().getSimpleName().replace("ThreeHandler","").toUpperCase(),
                Function.identity()
            )
        );
    }

    public ThreeHandler getStrategy(String callbackName) {
        return threeStrategies.get(callbackName);
    }
}

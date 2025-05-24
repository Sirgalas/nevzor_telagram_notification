package ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sergalas.notification.service.patterns.notificationManager.notificationManager.answerQuery.strategy.four.handler.FourHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FourStrategy {
    Map<String, FourHandler> threeStrategies;

    public FourStrategy(@Autowired List<FourHandler> strategyList) {
        threeStrategies = strategyList.stream().collect(
            Collectors.toMap(
                strategy -> strategy.getClass().getSimpleName().replace("ThreeHandler","").toUpperCase(),
                Function.identity()
            )
        );
    }

    public FourHandler getStrategy(String callbackName) {
        return threeStrategies.get(callbackName);
    }
}

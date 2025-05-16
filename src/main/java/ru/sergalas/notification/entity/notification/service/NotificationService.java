package ru.sergalas.notification.entity.notification.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.sergalas.notification.entity.notification.entity.Notification;

public interface NotificationService {
        Notification findById(String id);
}

package ru.sergalas.notification.entity.notification.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.enums.NotificationStatus;

public interface NotificationService {
        Notification findById(String id);

        void changeStatus(Notification notification, NotificationStatus status);
}

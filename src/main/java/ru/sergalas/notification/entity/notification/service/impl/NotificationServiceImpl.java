package ru.sergalas.notification.entity.notification.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import ru.sergalas.notification.entity.notification.entity.Notification;
import ru.sergalas.notification.entity.notification.repository.NotificationRepository;
import ru.sergalas.notification.entity.notification.service.NotificationService;
import ru.sergalas.notification.enums.NotificationStatus;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationServiceImpl implements NotificationService {
    NotificationRepository notificationRepository;

    @Override
    public Notification findById(String id) {
         return notificationRepository.findById(
             UUID.fromString(id)).orElseThrow(
                 ()  -> new NoSuchElementException("Notification with id %s not found".formatted(id))
         );
    }

    @Override
    public void changeStatus(Notification notification, NotificationStatus status) {
        notification.setStatus(NotificationStatus.WAITING);
        notificationRepository.save(notification);
    }

}
